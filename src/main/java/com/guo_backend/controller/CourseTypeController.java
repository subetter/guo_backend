package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.CourseType;
import com.guo_backend.service.CourseTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author fu
 * @Date 2022 12 23 22 44
 **/
@Component
@RestController
@RequestMapping("api/courseType")
@Slf4j
@Tag(name = "课程类别表")
public class CourseTypeController {
    @Resource
    private CourseTypeService courseTypeService;
    @GetMapping("all")
    @Operation(summary = "获取所有课程类别信息")
    public BaseResponse<List> getall(){
        return ResultUtils.success(courseTypeService.list());
    }
}
