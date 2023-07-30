package org.javaboy.tienchin.contract.service;

import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.contract.domain.Contract;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Odin
 * @since 2023-07-30
 */
public interface IContractService extends IService<Contract> {

    AjaxResult uploadContractFile(HttpServletRequest req, MultipartFile file);

    AjaxResult deleteContractFile(String year, String month, String day, String name);

    AjaxResult getContractInfoPhone(String phone);
}
