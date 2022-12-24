package com.guo_backend.service;

import com.guo_backend.domain.CourseResourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author fu
* @description 针对表【course_resourse】的数据库操作Service
* @createDate 2022-12-04 23:24:11
*/
public interface CourseResourseService extends IService<CourseResourse> {
    CourseResourse getPdf(String chapterId);
    CourseResourse getvideo(String chapterId,String courseId);
    CourseResourse getFirstVideo(String courseId);
}
