package com.guo_backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.domain.CourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo_backend.domain.dto.CourseinfoDto;

import java.util.List;

/**
* @author fu
* @description 针对表【course_info】的数据库操作Service
* @createDate 2022-12-04 23:24:01
*/
public interface CourseInfoService extends IService<CourseInfo> {
    CourseinfoDto getCourseList(String search, String typeId, Long currentPage);

}
