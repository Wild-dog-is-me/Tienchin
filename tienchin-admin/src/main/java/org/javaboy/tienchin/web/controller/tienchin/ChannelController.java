package org.javaboy.tienchin.web.controller.tienchin;

import org.javaboy.tienchin.channel.domain.Channel;
import org.javaboy.tienchin.channel.domain.vo.ChannelVO;
import org.javaboy.tienchin.channel.service.IChannelService;
import org.javaboy.tienchin.common.annotation.Log;
import org.javaboy.tienchin.common.core.controller.BaseController;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.page.TableDataInfo;
import org.javaboy.tienchin.common.enums.BusinessType;
import org.javaboy.tienchin.common.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Odin
 * @since 2023-07-22
 */
@RestController
@RequestMapping("/tienchin/channel")
public class ChannelController extends BaseController {

    @Resource
    private IChannelService channelService;

    @PreAuthorize("hasPermission('tienchin:channel:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChannelVO channelVO) {
        startPage();
        List<Channel> list = channelService.selectChannelList(channelVO);
        return getDataTable(list);
    }

    @PreAuthorize("hasPermission('tienchin:channel:create')")
    @Log(title = "渠道管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated ChannelVO channelVO) {
        return channelService.addChannel(channelVO);
    }

    @PreAuthorize("hasPermission('tienchin:channel:edit')")
    @Log(title = "渠道管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody @Validated ChannelVO channelVO) {
        return channelService.updateChannel(channelVO);
    }

    @PreAuthorize("hasPermission('tienchin:channel:edit')")
    @GetMapping("/{channelId}")
    public AjaxResult getInfo(@PathVariable Long channelId) {
        return AjaxResult.success(channelService.getById(channelId));
    }

    @PreAuthorize("hasPermition('tienchin:channel:remove')")
    @Log(title = "渠道管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{channelIds}")
    public AjaxResult remove(@PathVariable Long[] channelIds) {
        return toAjax(channelService.deleteChannelByIds(channelIds));
    }

    @PreAuthorize("hasPermission('tienchin:channel:export')")
    @Log(title = "渠道管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChannelVO channelVO) {
        List<Channel> list = channelService.selectChannelList(channelVO);
        ExcelUtil<Channel> util = new ExcelUtil<Channel>(Channel.class);
        util.exportExcel(response, list, "channel");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<Channel> util = new ExcelUtil<Channel>(Channel.class);
        util.importTemplateExcel(response, "渠道数据");
    }

    @PreAuthorize("hasPermition('tienchin:channel:import')")
    @Log(title = "渠道管理", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult importExcel(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Channel> util = new ExcelUtil<>(Channel.class);
        List<Channel> list = util.importExcel(file.getInputStream());
        return AjaxResult.success(channelService.importChannel(list, updateSupport));
    }
}
