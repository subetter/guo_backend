package com.guo_backend.controller;
import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ErrorCode;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.Chapter;
import com.guo_backend.service.impl.ChapterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Objects;
import java.util.prefs.BackingStoreException;

@RestController
@Component
@Slf4j
@Tag(name = "章节")
public class ChapterController {

    @Resource
    ChapterServiceImpl chapterService;

    public ChapterController(ChapterServiceImpl chapterService){
        this.chapterService = chapterService;
    }

    @PostMapping(value="/add/bigchapter")
    @Operation(summary = "新增大章节")
    public BaseResponse<Chapter> addBigChapter(Chapter chapter){
        return ResultUtils.success(chapterService.addChapter(chapter));
    }

    @PostMapping(value = "/add/littlechapter")
    @Operation(summary = "新增小章节")
    public BaseResponse<Chapter> addLittleChapter(Chapter chapter){
        return ResultUtils.success(chapterService.addLittleChapter(chapter));
    }

    @PostMapping(value = "/update/chapter")
    @Operation(summary = "更新章节")
    public BaseResponse<Boolean> updateChapter(Chapter chapter) {
        return ResultUtils.success(chapterService.updateChapter(chapter));
    }

    @Operation(summary = "获取章节信息")
    @RequestMapping(value = "/api/chapter/all")
    public BaseResponse<Chapter> getChapterInfo(@RequestParam String courseId){
        if (courseId==null){
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(chapterService.getChapterInfo(courseId));
    }
    @Operation(summary = "删除大章节")
    @DeleteMapping("/delete/bchapter/")
    public BaseResponse<Boolean> deleteChapter( @RequestBody List<String> chapterId){
        if (Objects.isNull(chapterId)){
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(chapterService.deleteBChapter(chapterId));
    }
    @Operation(summary = "删除小章节")
    @DeleteMapping("/delete/schapter/")
    public BaseResponse<Boolean> deletesChapter( @RequestBody List<String> chapterId){
        if (Objects.isNull(chapterId)){
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(chapterService.deleteSChapter(chapterId));
    }
}
