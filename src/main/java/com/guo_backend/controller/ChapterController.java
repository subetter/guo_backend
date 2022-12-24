package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.Chapter;
import com.guo_backend.service.ChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Component
@RestController
@Slf4j
@Tag(name = "章节")
public class ChapterController {
    @Resource
    private ChapterService chapterService;
    @Operation(summary = "获取章节信息")
    @RequestMapping(value = "/api/chapter/all")
    public BaseResponse<Chapter> getChapterInfo(@RequestParam String courseId){
        return ResultUtils.success(chapterService.getChapterInfo(courseId));
    }
}
