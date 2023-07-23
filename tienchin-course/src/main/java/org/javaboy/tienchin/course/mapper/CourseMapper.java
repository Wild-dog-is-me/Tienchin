package org.javaboy.tienchin.course.mapper;

import org.javaboy.tienchin.course.domin.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.javaboy.tienchin.course.domin.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectCourseList(CourseVO courseVO);
}
