package org.javaboy.tienchin.business.mapper;

import org.javaboy.tienchin.business.domain.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.javaboy.tienchin.business.domain.vo.BusinessSummary;
import org.javaboy.tienchin.business.domain.vo.BusinessVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Odin
 * @since 2023-07-28
 */
public interface BusinessMapper extends BaseMapper<Business> {

    List<BusinessSummary> selectBusinessList(BusinessVO businessVO);
}
