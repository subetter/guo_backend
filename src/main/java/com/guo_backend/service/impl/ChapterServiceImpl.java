package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.Chapter;
import com.guo_backend.domain.dto.ChapterDto;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}




