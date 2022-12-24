package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.service.CourseInfoService;
import io.swagger.v3.oas.annotations.Operation;
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
@Slf4j
@Tag(name = "课程信息表")
public class CourseController {
    @Resource
    private CourseInfoService courseInfoService;

    @Operation(summary = "获取对应的课程信息")
    @RequestMapping(value = "/api/course/getinfo",method = RequestMethod.GET)
    public BaseResponse<CourseInfo> getinfo( @RequestParam String courseId ){
        return ResultUtils.success(courseInfoService.getById(courseId));
    }
}
