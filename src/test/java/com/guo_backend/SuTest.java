package com.guo_backend;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CourseinfoDto;
import com.guo_backend.mapper.CourseInfoMapper;
import com.guo_backend.service.CourseResourseService;
import com.guo_backend.service.impl.CourseInfoServiceImpl;
import com.guo_backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SuTest {
    @Autowired
    UserServiceImpl usvc;

    // 测试注册功能
    @Test
    void testregister(){
        User user = new User();
        user.setUserName("刘昕蕊");
        user.setUserId("3");
        user.setAccount("20201120510");
        user.setPassword("1234567");
        user.setRoleId(0);
        user.setCreateTime(new Date());
        System.out.println(usvc.register(user));
    }

    @Resource
    CourseInfoMapper courseInfoMapper;

    @Test
    void test02() {
        // 根据课程名称关键词查询
        QueryWrapper<CourseInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("course_name", "")
                .eq("course_type","2");
//        List<CourseInfo> list = courseInfoMapper.selectList(queryWrapper);
        Page<CourseInfo> page = new Page<>(0,3);
        Page<CourseInfo> courselist = courseInfoMapper.selectPage(page,queryWrapper);
        System.out.println(courselist.toString());

    }

    @Resource
    CourseInfoServiceImpl csrv;
    // 测试 按条件分页查询课程信息
    @Test
    void test03(){
        CourseinfoDto courseinfoDto = csrv.getCourseList("计算机", "1", 0L);
        System.out.println("res:"+courseinfoDto.toString());
    }

    @Resource
    CourseResourseService courseResourseService;
    @Test
    void test04(){
        System.out.println(courseResourseService.getFirstVideo("1"));
    }


}
