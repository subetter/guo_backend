package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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


}




