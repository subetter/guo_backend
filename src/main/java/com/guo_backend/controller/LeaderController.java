package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.dto.Calc1Dto;
import com.guo_backend.domain.dto.ViewsCountDto;
import com.guo_backend.service.CourseResourseService;
import com.guo_backend.service.impl.CourseInfoServiceImpl;
import com.guo_backend.service.impl.CourseResourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Component
@RestController
@Slf4j
@Tag(name = "公司领导")
public class LeaderController {
    @Resource
    CourseResourseServiceImpl courseResourseService;

    @GetMapping("/views/count")
    @Operation(summary = "观看次数统计接口")
    public BaseResponse<ViewsCountDto> countViews(){
        return ResultUtils.success(courseResourseService.viewscount());
    }

    @Resource
    CourseInfoServiceImpl courseInfoService;

    @GetMapping("/typeratio")
    @Operation(summary = "各个课程类型占比")
    public BaseResponse<Calc1Dto> calc(){
        return ResultUtils.success(courseInfoService.calc());
    }

}
