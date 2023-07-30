package org.javaboy.tienchin.web.controller.tienchin;

import org.javaboy.tienchin.business.service.IBusinessService;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.contract.service.IContractService;
import org.javaboy.tienchin.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Odin
 * @since 2023-07-30
 */
@RestController
@RequestMapping("/tienchin/contract")
public class ContractController {

    @Resource
    IContractService contractService;

    @Resource
    ISysUserService sysUserService;

    @Resource
    IBusinessService businessService;

    @PreAuthorize("hasPermission('tienchin:contract:create')")
    @PostMapping("/upload")
    public AjaxResult uploadContractFile(HttpServletRequest req, MultipartFile file) {
        return contractService.uploadContractFile(req,file);
    }

    @PreAuthorize("hasPermission('tienchin:contract:create')")
    @DeleteMapping("/{year}/{month}/{day}/{name}")
    public AjaxResult deleteContractFile(@PathVariable String year, @PathVariable String month, @PathVariable String day, @PathVariable String name) {
        return contractService.deleteContractFile(year, month, day, name);
    }

    @GetMapping("/customer/{phone}")
    @PreAuthorize("hasPermission('tienchin:contract:create')")
    public AjaxResult geContractInfoPhone(@PathVariable String phone) {
        return contractService.getContractInfoPhone(phone);
    }

    @GetMapping("/users/{deptId}")
    @PreAuthorize("hasPermission('tienchin:contract:create')")
    public AjaxResult getUsersByDeptId(@PathVariable Long deptId) {
        return sysUserService.getUsersByDeptId(deptId);
    }


}
