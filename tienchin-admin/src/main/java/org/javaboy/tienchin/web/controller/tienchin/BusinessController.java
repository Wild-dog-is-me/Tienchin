package org.javaboy.tienchin.web.controller.tienchin;

import org.javaboy.tienchin.activity.service.IActivityService;
import org.javaboy.tienchin.business.domain.Business;
import org.javaboy.tienchin.business.domain.vo.BusinessSummary;
import org.javaboy.tienchin.business.domain.vo.BusinessVO;
import org.javaboy.tienchin.business.service.IBusinessService;
import org.javaboy.tienchin.channel.service.IChannelService;
import org.javaboy.tienchin.common.annotation.Log;
import org.javaboy.tienchin.common.core.controller.BaseController;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.page.TableDataInfo;
import org.javaboy.tienchin.common.enums.BusinessType;
import org.javaboy.tienchin.course.service.ICourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.print.PrinterAbortException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Odin
 * @since 2023-07-28
 */
@RestController
@RequestMapping("/tienchin/business")
public class BusinessController extends BaseController {

    @Resource
    private IBusinessService businessService;

    @Resource
    private IChannelService channelService;

    @Resource
    private IActivityService activityService;

    @Resource
    private ICourseService courseService;

    @PreAuthorize("hasPermission('tienchin:clue:create')")
    @Log(title = "商机管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Business business) {
        return businessService.addBusiness(business);
    }


    @PreAuthorize("hasPermission('tienchin:clue:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessVO businessVO) {
        startPage();
        List<BusinessSummary> list = businessService.selectBusinessList(businessVO);
        return getDataTable(list);
    }

    @PreAuthorize("hasPermission('tienchin:business:create')")
    @GetMapping("/channels")
    public AjaxResult getAllChannels() {
        return AjaxResult.success(channelService.list());
    }

    @PreAuthorize("hasPermission('tienchin:business:create')")
    @GetMapping("/activity/{channelId}")
    public AjaxResult getActivityByChannelId(@PathVariable Integer channelId) {
        return activityService.selectActivityByChannelId(channelId);
    }

}
