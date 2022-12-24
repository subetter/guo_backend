package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.domain.dto.CourseinfoDto;
import com.guo_backend.mapper.CourseInfoMapper;
import com.guo_backend.service.CourseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author subetter
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
     implements CourseInfoService{
    @Resource
    private CourseInfoMapper courseInfoMapper;

    //  根据条件分页查询课程信息
    @Override
    public CourseinfoDto getCourseList(String search, String typeId, Long currentPage) {
        try{
            QueryWrapper<CourseInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("course_name",search)
                    .eq("course_type",typeId);
            Page<CourseInfo> page = new Page<>(currentPage,30);
            Page<CourseInfo> courselist = courseInfoMapper.selectPage(page,queryWrapper);
            return CourseinfoDto.builder()
                    .pageSize(30L)
                    .currentPage(currentPage)
                    .records(page.getTotal())
                    .results(courselist)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

//          return courseInfoMapper.selectPage(page,queryWrapper);
    }



}




