package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.CourseType;
import com.guo_backend.service.CourseTypeService;
import com.guo_backend.mapper.CourseTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【course_type】的数据库操作Service实现
* @createDate 2022-12-04 23:24:26
*/
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType>
    implements CourseTypeService{

}




