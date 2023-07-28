package org.javaboy.tienchin.web.controller.tienchin.clue;

import org.javaboy.tienchin.assignment.domain.Assignment;
import org.javaboy.tienchin.assignment.service.IAssignmentService;
import org.javaboy.tienchin.business.domain.Business;
import org.javaboy.tienchin.business.service.IBusinessService;
import org.javaboy.tienchin.common.annotation.Log;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.enums.BusinessType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/tienchin/assignment")
public class AssignmentController {

    @Resource
    private IAssignmentService assignmentService;

    @Resource
    private IBusinessService businessService;

    @PostMapping
    public AjaxResult assignClue(@Validated @RequestBody Assignment assignment) {
        return assignmentService.assignClue(assignment);
    }


}
