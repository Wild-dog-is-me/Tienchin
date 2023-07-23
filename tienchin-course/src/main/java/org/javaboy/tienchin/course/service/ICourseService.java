package org.javaboy.tienchin.course.service;

import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.course.domin.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.javaboy.tienchin.course.domin.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
public interface ICourseService extends IService<Course> {

    List<Course> selectCourseList(CourseVO courseVO);

    AjaxResult addCourse(Course course);

    AjaxResult updateCourse(Course course);

    Boolean deleteCourseByIds(Long[] courseId);
}
