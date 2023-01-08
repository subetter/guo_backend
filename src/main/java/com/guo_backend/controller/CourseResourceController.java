package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ErrorCode;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.CourseResourse;
import com.guo_backend.service.CourseResourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

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
    public BaseResponse<CourseResourse> getPdf( @RequestParam String chapterId ) {
        if (chapterId == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(courseResourseService.getPdf(chapterId));
    }

    @Operation(summary = "获取课程的第一个视频")
    @GetMapping("/api/cr/covervideo")
    public BaseResponse<CourseResourse> getFirstVedio( @RequestParam String courseId ) {
        return ResultUtils.success(courseResourseService.getFirstVideo(courseId));
    }
    @Operation(summary = "更新课程资源")
    @GetMapping("/cr/update")
    public BaseResponse<CourseResourse> update( @RequestBody CourseResourse courseResourse) {
        if (Objects.isNull(courseResourse)){
            return  ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        CourseResourse courseResourse1=courseResourseService.updateInfo(courseResourse);
        if (courseResourse1==null){
            return ResultUtils.error(500,"更新失败");
        }
        return ResultUtils.success(courseResourse1);
    }

    @Operation(summary = "获取课程资源信息")
    @GetMapping("/cr/crinfo")
    public BaseResponse<CourseResourse> getInfo( @RequestParam String courseId, @RequestParam String chapterId ) {
        if (chapterId == null || courseId == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(courseResourseService.getinfo(courseId, chapterId));
    }

    @Operation(summary = "根据Id删除相应的课程资源")
    @DeleteMapping("/cr/delete")
    public BaseResponse<Boolean> deleteCR( @RequestBody String resourceId ) {
        if (resourceId == null) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(courseResourseService.deleteChapter(resourceId));
    }

    @Operation(summary = "新增PDF")
    @PostMapping("/cr/addpdf")
    public BaseResponse<CourseResourse> addPDF( @RequestBody String courseId, @RequestBody String chapterId, MultipartFile file ) {
        if (Objects.isNull(courseId) || Objects.isNull(chapterId) || Objects.isNull(file)) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        CourseResourse courseResourse = courseResourseService.addPDF(courseId, chapterId, file);
        if (Objects.isNull(courseResourse)) {
            return ResultUtils.error(500, "上传失败");
        }
        return ResultUtils.success(courseResourse);
    }

    @Operation(summary = "新增Video")
    @PostMapping("/cr/addvideo")
    public BaseResponse<CourseResourse> addVideo( @RequestBody String courseId, @RequestBody String chapterId, MultipartFile file ) {
        if (Objects.isNull(courseId) || Objects.isNull(chapterId) || Objects.isNull(file)) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        CourseResourse courseResourse = courseResourseService.addVideo(courseId, chapterId, file);
        if (Objects.isNull(courseResourse)) {
            return ResultUtils.error(500, "上传失败");
        }
        return ResultUtils.success(courseResourse);
    }
}
