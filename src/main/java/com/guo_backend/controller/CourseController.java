package com.guo_backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.domain.dto.CourseRequest;
import com.guo_backend.domain.dto.CourseinfoDto;
import com.guo_backend.service.CourseInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author fu
 * @Date 2022 12 23 23 01
 **/
@Component
@RestController
//@RequestMapping(value = "/course")
@Slf4j
@Tag(name = "课程信息表")
public class CourseController {

    @Resource
    private CourseInfoService courseInfoService;

    public CourseController(CourseInfoService courseInfoService){
        this.courseInfoService = courseInfoService;
    }

    @Operation(summary = "获取对应的课程信息")
    @RequestMapping(value = "/api/course/getinfo", method = RequestMethod.GET)
    public BaseResponse<CourseInfo> getinfo(@RequestParam String courseId) {
        return ResultUtils.success(courseInfoService.getById(courseId));
    }

    @Operation(summary = "根据关键词和类型分页查询课程信息")
    @RequestMapping(value = "/api/courseinfo/search",method = RequestMethod.POST)
    public BaseResponse<Object> searchCourse(@RequestBody CourseRequest req) {
        CourseinfoDto courselist = courseInfoService.getCourseList(req.getSearch(), req.getTypeId(), req.getCurrentPage());
        System.out.println("search:" + req.getSearch() + " typeId:" + req.getTypeId() + "current:" + req.getCurrentPage());
        System.out.println("courselist:" + courselist);
        return ResultUtils.success(courselist);
    }
}
