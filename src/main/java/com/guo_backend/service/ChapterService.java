package com.guo_backend.service;

import com.guo_backend.domain.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo_backend.domain.dto.ChapterDto;

import java.util.List;

/**
* @author fu
* @description 针对表【chapter】的数据库操作Service
* @createDate 2022-12-04 23:23:09
*/
public interface ChapterService extends IService<Chapter> {
    ChapterDto getChapterInfo(String courseId);

    public Chapter addChapter(Chapter chapter);

    public Chapter addLittleChapter(Chapter chapter);

    public Boolean updateChapter(Chapter chapter);
    boolean deleteBChapter( List<String> chapterIds );
    boolean deleteSChapter( List<String> chapterIds );
}
