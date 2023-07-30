package org.javaboy.tienchin.contract.mapper;

import org.javaboy.tienchin.contract.domain.Contract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.javaboy.tienchin.contract.domain.vo.ContractInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Odin
 * @since 2023-07-30
 */
public interface ContractMapper extends BaseMapper<Contract> {

    List<ContractInfo> getContractInfoPhone(String phone);

}
