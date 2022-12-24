package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.CourseResourse;
import com.guo_backend.domain.User;
import com.guo_backend.mapper.CourseResourseMapper;
import com.guo_backend.service.CourseResourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author fu
* @description 针对表【course_resourse】的数据库操作Service实现
* @createDate 2022-12-04 23:24:11
*/
@Service
public class CourseResourseServiceImpl extends ServiceImpl<CourseResourseMapper, CourseResourse>
    implements CourseResourseService {
    @Resource
    private CourseResourseMapper courseResourseMapper;
    @Override
    public CourseResourse getPdf( String chapterId ) {
        QueryWrapper<CourseResourse> queryWrapper = Wrappers.<CourseResourse>query()
                .eq("chapter_id", chapterId);
        return courseResourseMapper.selectOne(queryWrapper);
    }

    @Override
    public CourseResourse getvideo(String chapterId,String courseId) {
        QueryWrapper<CourseResourse> queryWrapper= Wrappers.<CourseResourse>query()
                .eq("course_id",courseId)
                .eq("chapter_id",chapterId);
        return courseResourseMapper.selectOne(queryWrapper);
    }


}




