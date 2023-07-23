package org.javaboy.tienchin.web.controller.tienchin;

import org.javaboy.tienchin.activity.domain.Activity;
import org.javaboy.tienchin.activity.domain.vo.ActivityVO;
import org.javaboy.tienchin.activity.service.IActivityService;
import org.javaboy.tienchin.channel.domain.Channel;
import org.javaboy.tienchin.channel.domain.vo.ChannelVO;
import org.javaboy.tienchin.channel.service.IChannelService;
import org.javaboy.tienchin.common.annotation.Log;
import org.javaboy.tienchin.common.core.controller.BaseController;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.page.TableDataInfo;
import org.javaboy.tienchin.common.enums.BusinessType;
import org.javaboy.tienchin.common.utils.poi.ExcelUtil;
import org.javaboy.tienchin.common.validator.CreateGroup;
import org.javaboy.tienchin.common.validator.EditGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@RestController
@RequestMapping("/tienchin/activity")
public class ActivityController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
    @Resource
    private IActivityService activityService;

    @Resource
    private IChannelService channelService;

    @PreAuthorize("hasPermission('tienchin:activity:create')")
    @GetMapping("/channel/list")
    public AjaxResult channelList() {
        return AjaxResult.success(channelService.selectChannelList(new ChannelVO()));
    }

    @PreAuthorize("hasPermission('tienchin:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityVO activityVO) {
        startPage();
        List<ActivityVO> list = activityService.selectActivityList(activityVO);
        logger.info("getDataTable:{}", getDataTable(list));
        return getDataTable(list);
    }

    @PreAuthorize("hasPermission('tienchin:actitvity:create')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(CreateGroup.class) @RequestBody ActivityVO activityVO) {
        return activityService.addActivity(activityVO);
    }

    @PreAuthorize("hasPermission('tienchin:activity:edit')")
    @GetMapping("/{activityId}")
    public AjaxResult getInfo(@PathVariable Long activityId) {
        return AjaxResult.success(activityService.getActivityById(activityId));
    }

    @PreAuthorize("hasPermission('tienchin:activity:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody ActivityVO activityVO) {
        return AjaxResult.success(activityService.updateActivity(activityVO));
    }

    @PreAuthorize("hasPermission('tienchin:activity:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds) {
        return toAjax(activityService.deleteActivityByIds(activityIds));
    }

    @PreAuthorize("hasPermission('tienchin:activity:export')")
    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityVO activityVO) {
        List<ActivityVO> list = activityService.selectActivityList(activityVO);
        ExcelUtil<ActivityVO> util = new ExcelUtil<>(ActivityVO.class);
        util.exportExcel(response,list,"活动数据");
    }
}
