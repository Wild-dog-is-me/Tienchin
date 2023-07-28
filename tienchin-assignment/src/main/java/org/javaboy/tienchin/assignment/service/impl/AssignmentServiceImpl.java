package org.javaboy.tienchin.assignment.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.javaboy.tienchin.assignment.domain.Assignment;
import org.javaboy.tienchin.assignment.mapper.AssignmentMapper;
import org.javaboy.tienchin.assignment.service.IAssignmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javaboy.tienchin.common.config.TienChinConfig;
import org.javaboy.tienchin.common.constant.TienChinConstants;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@Service
public class AssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements IAssignmentService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult assignClue(Assignment assignment) {
        try {
            // 1、先将一个线索中的所有分配记录 latest 设置为 false
            UpdateWrapper<Assignment> uw = new UpdateWrapper<>();
            uw.lambda().set(Assignment::getLatest, false).eq(Assignment::getAssignId, assignment.getAssignId());
            // 2、分配线索
            assignment.setType(TienChinConstants.CLUE_TYPE);
            assignment.setCreateBy(SecurityUtils.getUsername());
            assignment.setCreateTime(LocalDateTime.now());
            assignment.setLatest(true);
            save(assignment);
            return AjaxResult.success("线索分配成功");
        } catch (Exception e) {
            return AjaxResult.error("分配线索失败:{}", e.getMessage());
        }
    }
}
