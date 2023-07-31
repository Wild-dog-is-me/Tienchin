package org.javaboy.tienchin.clue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.javaboy.tienchin.assignment.domain.Assignment;
import org.javaboy.tienchin.assignment.service.IAssignmentService;
import org.javaboy.tienchin.business.domain.Business;
import org.javaboy.tienchin.business.service.IBusinessService;
import org.javaboy.tienchin.clue.domain.Clue;
import org.javaboy.tienchin.clue.domain.vo.ClueDetails;
import org.javaboy.tienchin.clue.domain.vo.ClueSummary;
import org.javaboy.tienchin.clue.domain.vo.ClueVO;
import org.javaboy.tienchin.clue.mapper.ClueMapper;
import org.javaboy.tienchin.clue.service.IClueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javaboy.tienchin.common.constant.TienChinConstants;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.domain.model.EchartsPoint;
import org.javaboy.tienchin.common.core.domain.model.LineChartData;
import org.javaboy.tienchin.common.utils.SecurityUtils;
import org.javaboy.tienchin.follow.domain.FollowRecord;
import org.javaboy.tienchin.follow.service.IFollowRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@Service
@Slf4j
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements IClueService {

    @Resource
    ClueMapper clueMapper;

    @Resource
    IAssignmentService assignmentService;

    @Resource
    private IFollowRecordService followRecordService;

    @Resource
    private IBusinessService businessService;

    @Override
    public AjaxResult addClue(Clue clue) {
        QueryWrapper<Clue> qw = new QueryWrapper<>();
        qw.lambda().eq(Clue::getPhone, clue.getPhone());
        Clue one = getOne(qw);
        if (one != null) {
            return AjaxResult.error("手机号码重复，线索录入失败");
        }
        clue.setNextTime(LocalDateTime.now().plusHours(TienChinConstants.NEXT_FOLLOW_TIME));
        clue.setCreateBy(SecurityUtils.getUsername());
        clue.setCreateTime(LocalDateTime.now());
        //添加线索
        save(clue);
        //添加线索默认的分配人
        Assignment assignment = new Assignment();
        assignment.setAssignId(clue.getClueId());
        assignment.setLatest(true);
        assignment.setType(TienChinConstants.CLUE_TYPE);
        assignment.setUserName(SecurityUtils.getUsername());
        assignment.setUserId(SecurityUtils.getUserId());
        assignment.setDeptId(SecurityUtils.getDeptId());
        assignment.setCreateBy(SecurityUtils.getUsername());
        assignment.setCreateTime(LocalDateTime.now());
        assignmentService.save(assignment);
        return AjaxResult.success("线索录入成功");
    }

    @Override
    public List<ClueSummary> selectClueList(ClueVO clueVO) {
        return clueMapper.selectClueList(clueVO);
    }

    @Override
    public AjaxResult getClueDetailsByClueId(Integer clueId) {
        ClueDetails cd = clueMapper.getClueDetailsByClueId(clueId);
        return AjaxResult.success(cd);
    }

    @Override
    public AjaxResult clueFollow(ClueDetails clueDetails) {
        Clue clue = new Clue();
        BeanUtils.copyProperties(clueDetails,clue);
        clue.setStatus(TienChinConstants.CLUE_FOLLOWING);
        updateById(clue);

        FollowRecord record = new FollowRecord();
        record.setAssignId(clueDetails.getClueId());
        record.setCreateBy(SecurityUtils.getUsername());
        record.setCreateTime(LocalDateTime.now());
        record.setType(TienChinConstants.CLUE_TYPE);
        record.setInfo(clueDetails.getRecord());
        followRecordService.save(record);
        return AjaxResult.success("线索跟进成功");
    }

    @Override
    @Transactional
    public AjaxResult invalidClueFollow(ClueDetails clueDetails) {
        Clue c = getById(clueDetails.getClueId());
        log.info("clue_info:{}", c);
        if (c.getFailCount() == 3) {
            // 无效线索次数已经达到极限
            UpdateWrapper<Clue> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().set(Clue::getStatus, TienChinConstants.CLUE_INVALIDATE).eq(Clue::getClueId, clueDetails.getClueId());
            update(updateWrapper);
            return AjaxResult.success("无效线索设置成功");
        }
        UpdateWrapper<Clue> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(Clue::getFailCount, clueDetails.getFailCount()+1).eq(Clue::getClueId, clueDetails.getClueId());

        FollowRecord record = new FollowRecord();
        record.setInfo(clueDetails.getRecord());
        record.setType(TienChinConstants.CLUE_TYPE);
        record.setCreateTime(LocalDateTime.now());
        record.setCreateBy(SecurityUtils.getUsername());
        record.setAssignId(clueDetails.getClueId());
        followRecordService.save(record);
        return AjaxResult.success("无效线索设置成功");
    }

    @Override
    public AjaxResult getClueSummaryByClueId(Integer clueId) {
        return AjaxResult.success(getById(clueId));
    }

    @Override
    public AjaxResult updateClue(Clue clue) {
        return updateById(clue) ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");
    }

    @Override
    public AjaxResult deleteClueById(Integer[] clueIds) {
        UpdateWrapper<Clue> uw = new UpdateWrapper<>();
        uw.lambda().set(Clue::getDelFlag, 1).eq(Clue::getClueId, clueIds);
        return update(uw) ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");
    }

    @Override
    @Transactional
    public AjaxResult clue2Business(Integer clueId) {
        Clue clue = getById(clueId);
        Business business = new Business();
        BeanUtils.copyProperties(clue, business);
        business.setCreateBy(SecurityUtils.getUsername());
        business.setCreateTime(LocalDateTime.now());
        business.setEndTime(null);
        business.setFailCount(0);
        business.setNextTime(null);
        business.setRemark(null);
        business.setUpdateBy(null);
        business.setUpdateTime(null);
        business.setNextTime(LocalDateTime.now().plusHours(TienChinConstants.NEXT_FOLLOW_TIME));
        business.setStatus(TienChinConstants.BUSINESS_ALLOCATED);
        //1. 删除线索
        UpdateWrapper<Clue> uw = new UpdateWrapper<>();
        uw.lambda().set(Clue::getDelFlag, 1).eq(Clue::getClueId, clueId);
        update(uw);
        //2。添加商机
        businessService.save(business);
        //3. 默认情况下，将商机分配给 admin，将来由 admin再将商机分配给不同的客户专员
        Assignment assignment = new Assignment();
        assignment.setUserName(TienChinConstants.AMDIN_USERNAME);
        assignment.setType(TienChinConstants.BUSINESS_TYPE);
        assignment.setCreateBy(SecurityUtils.getUsername());
        assignment.setCreateTime(LocalDateTime.now());
        assignment.setAssignId(business.getBusinessId());
        assignment.setDeptId(TienChinConstants.ADMIN_DEPT_ID);
        assignment.setUserId(TienChinConstants.ADMIN_ID);
        assignment.setLatest(true);
        assignmentService.save(assignment);
        return AjaxResult.success("线索成功转为商机");
    }

    @Override
    public AjaxResult clueAnalysisData(ClueVO clueVO) {
        if (clueVO.getParams().get("beginTime") == null || clueVO.getParams().get("endTime") == null) {
            clueVO.getParams().put("beginTime", LocalDateTime.now().minusWeeks(1));
            clueVO.getParams().put("endTime", LocalDateTime.now().plusWeeks(1));
        }
        List<EchartsPoint> increaseClue = clueMapper.increaseClue(clueVO);
        List<EchartsPoint> totalClue = clueMapper.totalClue(clueVO);
        LineChartData lineChartData = new LineChartData();
        lineChartData.setIncrease(increaseClue);
        lineChartData.setTotal(totalClue);
        return AjaxResult.success(lineChartData);
    }

}
