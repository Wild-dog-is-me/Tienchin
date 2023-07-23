package org.javaboy.tienchin.web.controller.tienchin;

import org.javaboy.tienchin.common.annotation.Log;
import org.javaboy.tienchin.common.core.controller.BaseController;
import org.javaboy.tienchin.common.core.domain.AjaxResult;
import org.javaboy.tienchin.common.core.page.TableDataInfo;
import org.javaboy.tienchin.common.enums.BusinessType;
import org.javaboy.tienchin.common.utils.poi.ExcelUtil;
import org.javaboy.tienchin.common.validator.EditGroup;
import org.javaboy.tienchin.course.domin.Course;
import org.javaboy.tienchin.course.domin.vo.CourseVO;
import org.javaboy.tienchin.course.service.ICourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@RestController
@RequestMapping("/tienchin/course")
public class CourseController extends BaseController {

    @Resource
    private ICourseService courseService;

    @PreAuthorize("hasPermission('tienchin:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourseVO courseVO) {
        startPage();
        List<Course> list = courseService.selectCourseList(courseVO);
        return getDataTable(list);
    }

    @PreAuthorize("hasPermission('tienchin:course:create')")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PreAuthorize("hasPermission('tienchin:course:edit')")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    @PreAuthorize("hasPermission('tienchin:course:edit')")
    @GetMapping("/{courseId}")
    public AjaxResult getInfo(@PathVariable Long courseId) {
        return AjaxResult.success(courseService.getById(courseId));
    }

    @PreAuthorize("hasPermission('tienchin:course:remove')")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseId}")
    public AjaxResult remove(@PathVariable Long[] courseId) {
        return toAjax(courseService.deleteCourseByIds(courseId));
    }

    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("hasPermission('tienchin:course:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseVO courseVO) {
        List<Course> list = courseService.selectCourseList(courseVO);
        ExcelUtil<Course> util = new ExcelUtil<>(Course.class);
        util.exportExcel(response, list, "课程数据");
    }
}
