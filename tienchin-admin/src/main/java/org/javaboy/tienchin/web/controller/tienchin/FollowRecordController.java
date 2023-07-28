package org.javaboy.tienchin.web.controller.tienchin;

import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.follow.service.IFollowRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/7/24 08:22
 * @Description:
 */

@RestController
@RequestMapping("/tienchin/follow/record")
public class FollowRecordController {

    @Resource
    private IFollowRecordService followRecordService;

     @GetMapping("/clue/{clueId}")
    public AjaxResult getFollowRecordByClueId(@PathVariable Integer clueId) {
        return followRecordService.getFollowRecordByClueId(clueId);
    }

}
