package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.domain.CourseType;
import com.guo_backend.domain.dto.Calc;
import com.guo_backend.domain.dto.Calc1Dto;
import com.guo_backend.domain.dto.CourseinfoDto;
import com.guo_backend.domain.dto.ViewsCount;
import com.guo_backend.mapper.CourseInfoMapper;
import com.guo_backend.mapper.CourseTypeMapper;
import com.guo_backend.service.CourseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author subetter
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
        implements CourseInfoService {
    @Resource
    private CourseInfoMapper courseInfoMapper;

    //  根据条件分页查询课程信息
    @Override
    public CourseinfoDto getCourseList(String search, String typeId, Long currentPage) {
        try {
            QueryWrapper<CourseInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("course_name", search)
                    .eq("course_type", typeId);
            Page<CourseInfo> page = new Page<>(currentPage, 30);
            Page<CourseInfo> courselist = courseInfoMapper.selectPage(page, queryWrapper);
            return CourseinfoDto.builder()
                    .pageSize(30L)
                    .currentPage(currentPage)
                    .records(page.getTotal())
                    .results(courselist)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Resource
    CourseTypeMapper courseTypeMapper;

    //  各个课程类型占比统计
    @Override
    public Calc1Dto calc() {
        try {
            // 根据 type_id 将 course_info分组
            QueryWrapper<CourseInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("course_type,count(*) as typeCount");
            queryWrapper.groupBy("course_type");
            List<Map<String, Object>> maps = courseInfoMapper.selectMaps(queryWrapper);
            List<Calc> list = new ArrayList<>();
            for (Map map : maps) {
                Set<Map.Entry<String, Object>> entry = map.entrySet();
                Calc calc = new Calc();
                for (Map.Entry<String, Object> value1 : entry) {
                    System.out.println("key:" + value1.getKey() + ",value:" + value1.getValue());
                    // 根据 course_type去type中找到 typename 赋值给 calc1.type_name
                    if ("course_type".equals(value1.getKey())) {
                        QueryWrapper<CourseType> queryWrapper1 = new QueryWrapper<>();
                        queryWrapper1.eq("type_id", value1.getValue());
                        CourseType ct = courseTypeMapper.selectOne(queryWrapper1);
                        calc.setTypeName(ct.getTypeName());
                    } else {
                        calc.setQuantity((Long) value1.getValue());
                    }
                }
                list.add(calc);
            }
            maps.forEach(System.out::println);
            return Calc1Dto.builder()
                    .res(list)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateCourseInfo(CourseInfo courseInfo) {
        boolean flag;
        QueryWrapper<CourseInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseInfo.getCourseId());
        CourseInfo courseInfo1=courseInfoMapper.selectOne(queryWrapper);
        courseInfo1.setCourseName(courseInfo.getCourseName());
        courseInfo1.setLogContent(courseInfo.getLogContent());
        courseInfo1.setCourseType(courseInfo.getCourseType());
        courseInfo1.setChapterQuantity(courseInfo.getChapterQuantity());
        courseInfo1.setCoverImg(courseInfo.getCoverImg());
        UpdateWrapper<CourseInfo> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("course_name",courseInfo1.getCourseName());
        updateWrapper.set("log_content",courseInfo1.getLogContent());
        updateWrapper.set("course_type",courseInfo1.getCourseType());
        updateWrapper.set("chapter_quantity",courseInfo1.getChapterQuantity());
        updateWrapper.set("cover_img",courseInfo1.getCoverImg());
        int results=courseInfoMapper.update(courseInfo1,queryWrapper);
        if(results>0){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }

    @Override
    public Boolean deleteCourse(String courseId) {
        return this.removeById(courseId);
    }


}




