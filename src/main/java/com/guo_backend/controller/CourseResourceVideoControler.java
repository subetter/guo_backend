package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.CourseResourse;
import com.guo_backend.service.CourseResourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Component
@RestController
@RequestMapping("student")
@Slf4j
@Tag(name="课程资源表")
@CrossOrigin
public class CourseResourceVideoControler {
    @Resource
    private CourseResourseService courseResourseService;
    @PostMapping("video")
    public BaseResponse<CourseResourse> getvideo(@RequestParam String chapterId,@RequestParam String courseId){
        return ResultUtils.success(courseResourseService.getvideo(chapterId, courseId));
    }

}
