package com.guo_backend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.guo_backend.domain.Chapter;
import com.guo_backend.domain.User;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.service.impl.UserServiceImpl;
import kotlin.jvm.internal.Lambda;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.desktop.QuitResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void test01(){
        // 条件构造器组装查询条件
        // 查询用户名包含a
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("chapter_name","a")
                .between("pre_chapter_id","1","2")
                .isNotNull("course_id");

        List<Chapter> list = chapterMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    // 条件构造器组装排序条件
    @Test
    public void test02(){
        // 查询章节信息，按照父章节id降序排序，若父章节id相同，则按照章节id升序排序\
        // SELECT chapter_id,chapter_name,pre_chapter_id,course_id,is_deleted FROM chapter WHERE is_deleted=0 ORDER BY pre_chapter_id DESC,chapter_id ASC
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("pre_chapter_id")
                .orderByAsc("chapter_id");
        List<Chapter> list = chapterMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test03(){
        // 删除courseid为 null的信息
//        UPDATE chapter SET is_deleted=1 WHERE is_deleted=0 AND (course_id IS NULL)
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("course_id");
        int result = chapterMapper.delete(queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    void test04(){
        // 将（course_id大于1并且章节名中包含测试）或父章节id为null的章节信息修改
        //UPDATE chapter SET chapter_name=?, course_id=? WHERE is_deleted=0 AND (course_id > ? AND chapter_name LIKE ? OR pre_chapter_id IS NULL)
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("course_id",1)
                        .like("chapter_name","测试")
                                .or()
                                        .isNull("pre_chapter_id");
        Chapter chapter =  new Chapter();
        chapter.setChapterName("test");
        chapter.setCourseId("6");
        int res = chapterMapper.update(chapter,queryWrapper);
        System.out.println("result:"+res);
    }

    //条件优先级
    @Test
    void test05(){
//        将course_id大于1并且(章节名中包含测试或父章节id为null)的章节信息修改
        // lamda表达式中的条件优先执行
//        UPDATE chapter SET chapter_name=?, course_id=? WHERE is_deleted=0 AND (course_id > ? AND (chapter_name LIKE ? OR pre_chapter_id IS NULL))
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("course_id",1)
                .and(i->i.like("chapter_name","测试").or().isNull("pre_chapter_id"));
        Chapter chapter =  new Chapter();
        chapter.setChapterName("test");
        chapter.setCourseId("6");
        int res = chapterMapper.update(chapter,queryWrapper);
        System.out.println("result:"+res);
//        UPDATE chapter SET chapter_name=?, course_id=? WHERE is_deleted=0 AND (course_id > ? AND (chapter_name LIKE ? OR pre_chapter_id IS NULL))
    }

    // 只查某些字段
    @Test
    void test06(){
        // 查询章节名称和上一章节的id
        // SELECT chapter_name,pre_chapter_id FROM chapter WHERE is_deleted=0
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("chapter_name","pre_chapter_id");
        List<Map<String,Object>> maps = chapterMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
    @Test
    void test07(){
        // 查询id <= 100的用户信息 子查询
        // SELECT chapter_id,chapter_name,pre_chapter_id,course_id,is_deleted FROM chapter WHERE is_deleted=0 AND (chapter_id IN (select chapter_id from chapter where chapter_id <= 100))
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("chapter_id","select chapter_id from chapter where chapter_id <= 100");
        List<Chapter> list = chapterMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test08(){
//        UPDATE chapter SET chapter_name=?,course_id=? WHERE is_deleted=0 AND (course_id > ? AND (chapter_name LIKE ? OR pre_chapter_id IS NULL))
        //        将course_id大于1并且(章节名中包含测试或父章节id为null)的章节信息修改
        UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("course_id","1")
                .and(i->i.like("chapter_name","测试").or().isNull("pre_chapter_id"));
        updateWrapper.set("chapter_name","测试123").set("course_id","22222");
        int result = chapterMapper.update(null,updateWrapper);
        System.out.println("result:"+result);
    }

    @Test
    void test09(){
        String chapter_name = "";
        String course_begin= "1";
        String course_end = "10";
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(chapter_name)) {
//            isNotBlank判断某个字符是否不为空字符串，不为null,不为空白符
            queryWrapper.like("chapter_name",chapter_name);
        }
        if(StringUtils.isNotBlank(course_begin)){
            queryWrapper.ge("course_id",course_begin);
        }
        if (StringUtils.isNotBlank(course_end)){
            queryWrapper.le("course_id",course_end);
        }
        List<Chapter> list = chapterMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test10(){
//        动态组装条件
        String chapter_name = "";
        String course_begin= "1";
        String course_end = "10";
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(chapter_name),"chapter_name",chapter_name)
                .ge(StringUtils.isNotBlank(course_begin),"course_id",course_begin)
                .le(StringUtils.isNotBlank(course_begin),"course_id",course_end);

        List<Chapter> list = chapterMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test11(){
        String chapter_name = "";
        String course_begin= "1";
        String course_end = "10";
//     防止字段名写错，提供函数式接口，返回字段
        LambdaQueryWrapper<Chapter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(chapter_name),Chapter::getChapterName,chapter_name)
                .ge(StringUtils.isNotBlank(course_begin),Chapter::getChapterId,course_begin)
                .le(StringUtils.isNotBlank(course_end),Chapter::getCourseId,course_end);

        List<Chapter> list = chapterMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test12(){
//        UPDATE chapter SET chapter_name=?,course_id=? WHERE is_deleted=0 AND (course_id > ? AND (chapter_name LIKE ? OR pre_chapter_id IS NULL))
        //        将course_id大于1并且(章节名中包含测试或父章节id为null)的章节信息修改
        LambdaUpdateWrapper<Chapter> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.gt(Chapter::getChapterId,"1")
                .and(i->i.like(Chapter::getChapterName,"测试").or().isNull(Chapter::getPreChapterId));
        updateWrapper.set(Chapter::getChapterName,"测试123").set(Chapter::getCourseId,"22222");
        int result = chapterMapper.update(null,updateWrapper);
        System.out.println("result:"+result);
    }



}
