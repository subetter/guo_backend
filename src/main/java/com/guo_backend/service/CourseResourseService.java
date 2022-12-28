package com.guo_backend.service;

import com.guo_backend.domain.CourseResourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo_backend.domain.dto.ViewsCountDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author fu
* @description 针对表【course_resourse】的数据库操作Service
* @createDate 2022-12-04 23:24:11
*/
public interface CourseResourseService extends IService<CourseResourse> {
    CourseResourse getPdf(String chapterId);
    CourseResourse getvideo(String chapterId,String courseId);
    CourseResourse getFirstVideo(String courseId);
    CourseResourse getinfo(String courseId,String chapterId);
    Boolean deleteChapter(String resourceId);
    CourseResourse updateInfo(CourseResourse courseResourse);
    CourseResourse addPDF( String courseId, String chapterId, MultipartFile file );
    CourseResourse addVideo( String courseId, String chapterId, MultipartFile file );
    // 观看次数统计
    ViewsCountDto viewscount();
}
