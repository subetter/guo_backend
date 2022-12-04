package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.mapper.CourseInfoMapper;
import com.guo_backend.service.CourseInfoService;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【course_info】的数据库操作Service实现
* @createDate 2022-12-04 23:24:01
*/
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
    implements CourseInfoService{

}




