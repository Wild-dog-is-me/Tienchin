package org.javaboy.tienchin.web.controller.tienchin.clue;

import org.javaboy.tienchin.clue.domain.Clue;
import org.javaboy.tienchin.clue.service.IClueService;
import org.javaboy.tienchin.common.annotation.Log;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.enums.BusinessType;
import org.javaboy.tienchin.common.validator.CreateGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */

@RestController
@RequestMapping("/tienchin/clue")
public class ClueController {

    @Resource
    private IClueService clueService;

    @PreAuthorize("hasPermission('tienchin:clue:create')")
    @Log(title = "线索管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(CreateGroup.class) @RequestBody Clue clue) {
        return clueService.addClue(clue);
    }
}
