package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.CourseResourse;
import com.guo_backend.service.CourseResourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author fu
 * @Date 2022 12 23 23 27
 **/
@Component
@RestController
//@RequestMapping("cr")
@Slf4j
@Tag(name = "课程资源表")
public class CourseResourceController {
    @Resource
    private CourseResourseService courseResourseService;

    @PostMapping("/cr/pdf")
    @Operation(summary = "下载课程的PDF")
    public BaseResponse<CourseResourse> getPdf(@RequestParam String chapterId) {
        return ResultUtils.success(courseResourseService.getPdf(chapterId));
    }

    @Operation(summary = "获取课程的第一个视频")
    @GetMapping("/api/cr/covervideo")
    public BaseResponse<CourseResourse> getFirstVedio(@RequestParam String courseId){
        return ResultUtils.success(courseResourseService.getFirstVideo(courseId));
    }
}
