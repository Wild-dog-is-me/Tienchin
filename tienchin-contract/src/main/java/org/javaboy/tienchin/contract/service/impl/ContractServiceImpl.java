package org.javaboy.tienchin.contract.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.javaboy.tienchin.business.service.IBusinessService;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.domain.UploadFileResp;
import org.javaboy.tienchin.contract.domain.Contract;
import org.javaboy.tienchin.contract.domain.vo.ContractInfo;
import org.javaboy.tienchin.contract.mapper.ContractMapper;
import org.javaboy.tienchin.contract.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javaboy.tienchin.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-30
 */
@Slf4j
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

    @Resource
    IBusinessService businessService;

    @Resource
    ICourseService courseService;

    @Resource
    ContractMapper contractMapper;

    @Resource
    RuntimeService runtimeService;

    @Resource
    TaskService taskService;

    @Resource
    HistoryService historyService;

    @Value("${tienchin.contract.file}")
    String contractFolder;

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @Override
    public AjaxResult uploadContractFile(HttpServletRequest req, MultipartFile file) {
        String format = sdf.format(new Date());
        String fileDir = contractFolder + format;
        File dir = new File(fileDir);
        if (!dir.exists()) {
            //如果文件夹不存在，那么就将文件夹创建出来
            dir.mkdirs();
        }
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名
        String newName = UUID.randomUUID().toString() + "-" + originalFilename;
        try {
            file.transferTo(new File(dir, newName));
            String url = req.getScheme() + "://"
                    + req.getServerName() + ":"
                    + req.getServerPort()
                    + req.getContextPath()
                    + "/tienchin/contract/views"
                    + format + newName;
            UploadFileResp resp = new UploadFileResp();
            resp.setName(originalFilename);
            resp.setUrl(url);
            return AjaxResult.success(resp);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
        return AjaxResult.error("文件上传失败");
    }

    @Override
    public AjaxResult deleteContractFile(String year, String month, String day, String name) {
        String fileName = contractFolder + File.separator + year + File.separator + month + File.separator + day + File.separator + name;
        File file = new File(fileName);
        boolean delete = file.delete();
        return delete ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }

    @Override
    public AjaxResult getContractInfoPhone(String phone) {
        List<ContractInfo> list = contractMapper.getContractInfoPhone(phone);
        if (list != null && list.size() > 0) {
            ContractInfo contractInfo = list.get(0);
            return AjaxResult.success(contractInfo);
        } else {
            return AjaxResult.error("手机号码输入错误，客户不存在");
        }
    }
}
