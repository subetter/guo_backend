package com.guo_backend;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.domain.Chapter;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CourseinfoDto;
import com.guo_backend.mapper.CourseInfoMapper;
import com.guo_backend.service.CourseResourseService;
import com.guo_backend.service.impl.ChapterServiceImpl;
import com.guo_backend.service.impl.CourseInfoServiceImpl;
import com.guo_backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.spi.CharsetProvider;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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


    @Resource
    ChapterServiceImpl chapterService;
//    测试添加大章节
    @Test
    void testadd1(){
        Chapter chapter = new Chapter();
        chapter.setChapterName("第五章 软件架构风格");
        chapter.setCourseId("1");
        chapter.setPreChapterId("123");
        System.out.println(chapterService.addChapter(chapter));
    }

// 测试添加第一个小章节
    @Test
    void testadd2(){
        Chapter chapter = new Chapter();
        chapter.setChapterName("5.1 分层架构描述");
        chapter.setCourseId("1");
        chapter.setRootChapterId("e1bf55fb-4e00-42fc-8fc0-c42872393f73");
        // 大章节下的第一个小章节，pre_id等于自己的 id
        chapter.setIsDeleted(0);
        System.out.println(chapterService.addLittleChapter(chapter));
    }

    // 测试添加第二个小章节
    @Test
    void testadd3(){
        Chapter chapter = new Chapter();
        chapter.setChapterName("5.2 领域模型设计");
        chapter.setCourseId("1");
        chapter.setRootChapterId("e1bf55fb-4e00-42fc-8fc0-c42872393f73");
        chapter.setPreChapterId("6cc591eb-eda0-4ff2-ab80-8fd71de8ac79");
        System.out.println(chapterService.addLittleChapter(chapter));
    }

    // 测试更新章节
    @Test
    void testupdate(){
        Chapter chapter = new Chapter();
        chapter.setChapterName("5.2 领域模型设计修改版");
        chapter.setChapterId("78ec8571-95a7-4221-869c-3071042c40cb");
        System.out.println(chapterService.updateChapter(chapter));
    }
}
