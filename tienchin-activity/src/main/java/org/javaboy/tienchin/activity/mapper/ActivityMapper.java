package org.javaboy.tienchin.activity.mapper;

import org.javaboy.tienchin.activity.domain.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.javaboy.tienchin.activity.domain.vo.ActivityVO;
import org.javaboy.tienchin.common.core.domain.model.PieData;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    List<ActivityVO> selectActivityList(ActivityVO activityVO);

    List<PieData> activityAnalysisData(ActivityVO activityVO);
}
