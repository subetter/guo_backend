package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.Chapter;
import com.guo_backend.domain.dto.ChapterDto;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import javax.annotation.Resource;
import java.util.UUID;

/**
* @author fu
* @description 针对表【chapter】的数据库操作Service实现
* @createDate 2022-12-04 23:23:09
*/
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
    implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public Chapter getChapterInfo(String courseId) {
        try{
            QueryWrapper<Chapter> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("course_id",courseId);
            List<Chapter> chapterList=chapterMapper.selectList(queryWrapper);
            return (Chapter) chapterList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 新增大章节
    @Override
    public Chapter addChapter(Chapter chapter){
        try{
            Chapter nchapter = new Chapter();
            nchapter.setChapterName(chapter.getChapterName());
            nchapter.setCourseId(chapter.getCourseId());
            nchapter.setPreChapterId(chapter.getPreChapterId());
            nchapter.setRootChapterId("0");
            nchapter.setIsDeleted(0);
            String uuid = UUID.randomUUID().toString();
            nchapter.setChapterId(uuid);
            chapterMapper.insert(nchapter);
            return nchapter;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    新增小章节
    @Override
    public Chapter addLittleChapter(Chapter chapter){
        try{
            Chapter nchapter = new Chapter();
            nchapter.setChapterName(chapter.getChapterName());
            nchapter.setCourseId(chapter.getCourseId());
            nchapter.setRootChapterId(chapter.getRootChapterId());
            String uuid = UUID.randomUUID().toString();
            nchapter.setChapterId(uuid);
            if(chapter.getPreChapterId() == null){
                nchapter.setPreChapterId(uuid);
            }else{
                nchapter.setPreChapterId(chapter.getPreChapterId());
            }
            nchapter.setIsDeleted(0);
            chapterMapper.insert(nchapter);
            return nchapter;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateChapter(Chapter chapter) {
        try{
            QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("chapter_id",chapter.getChapterId());
            Chapter chapter1 = chapterMapper.selectOne(queryWrapper);
            chapter1.setChapterName(chapter.getChapterName());
            UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("chapter_name",chapter1.getChapterName());
            int res = chapterMapper.update(chapter1,queryWrapper);
            if(res>0) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}




