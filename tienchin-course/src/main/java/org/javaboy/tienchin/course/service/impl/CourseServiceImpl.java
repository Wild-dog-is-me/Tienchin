package org.javaboy.tienchin.course.service.impl;

import org.javaboy.tienchin.course.domin.Course;
import org.javaboy.tienchin.course.mapper.CourseMapper;
import org.javaboy.tienchin.course.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
