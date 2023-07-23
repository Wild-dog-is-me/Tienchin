package org.javaboy.tienchin.activity.service;

import org.javaboy.tienchin.activity.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.javaboy.tienchin.activity.domain.vo.ActivityVO;
import org.javaboy.tienchin.common.core.domain.AjaxResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
public interface IActivityService extends IService<Activity> {

    List<ActivityVO> selectActivityList(ActivityVO activityVO);

    AjaxResult addActivity(ActivityVO activityVO);

    ActivityVO getActivityById(Long activityId);
}
