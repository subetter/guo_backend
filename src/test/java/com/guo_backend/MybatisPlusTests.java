package com.guo_backend;


import com.guo_backend.domain.Chapter;
import com.guo_backend.domain.User;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.mapper.UserMapper;
import net.minidev.json.JSONUtil;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTests {

    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void testSelectList() {
        // 通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<Chapter> list = chapterMapper.selectList(null);
        list.forEach(System.out::println);
    }

    // 新增章节信息
    // INSERT INTO chapter ( chapter_id, chapter_name, pre_chapter_id, course_id ) VALUES ( ?, ?, ?, ? )
    @Test
    public void testInsert() {
        Chapter chapter = new Chapter();
        chapter.setChapterId("3");
        chapter.setChapterName("测试2");
        chapter.setCourseId("3");
        chapter.setPreChapterId("1");
        int result = chapterMapper.insert(chapter);
        System.out.println("result: " + result);
    }

    // 删除方法
    @Test
    public void testDelete() {
//        DELETE FROM chapter WHERE chapter_id=?
//        int result = chapterMapper.deleteById("1");
//        System.out.println(result);

//       DELETE FROM chapter WHERE course_id = ? AND chapter_name = ?
//       map里面放的是删除的条件,根据map集合中所设置的条件删除章节信息
//        Map<String, Object> map = new HashMap<>();
//        map.put("chapter_name", "测试2");
//        map.put("course_id", "3");
//        int result = chapterMapper.deleteByMap(map);
//        System.out.println("result:" + result);

        // 通过多个id实现批量删除
        //  DELETE FROM chapter WHERE chapter_id IN ( ? , ? , ? )

//        逻辑删除  UPDATE chapter SET is_deleted=1 WHERE chapter_id IN ( ? , ? , ? ) AND is_deleted=0
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        int res = chapterMapper.deleteBatchIds(list);
        System.out.println("res:" + res);
    }

//    修改章节信息
//    UPDATE chapter SET chapter_name=?, course_id=? WHERE chapter_id=?
    @Test
    public void testUpdate() {
        Chapter chapter = new Chapter();
        chapter.setChapterId("4");
        chapter.setChapterName("第四章");
        chapter.setCourseId("111122223");
        int result = chapterMapper.updateById(chapter);
        System.out.println(result);
    }

    @Test
    public void testSelect(){
        // 通过id查询章节信息
//        SELECT chapter_id,chapter_name,pre_chapter_id,course_id FROM chapter WHERE chapter_id=?
        /*Chapter chapter = chapterMapper.selectById(1);
        System.out.println(chapter);*/

        // 根据多个id批量查询多个章节信息
        // SELECT chapter_id,chapter_name,pre_chapter_id,course_id FROM chapter WHERE chapter_id IN ( ? , ? , ? )
        /*List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<Chapter> lists = chapterMapper.selectBatchIds(list);
        lists.forEach(System.out::println);*/

//SELECT chapter_id,chapter_name,pre_chapter_id,course_id FROM chapter WHERE pre_chapter_id = ? AND chapter_name = ?
        /*Map<String,Object> map = new HashMap<>();
        map.put("chapter_name","测试1");
        map.put("pre_chapter_id","0");

        List<Chapter> list  = chapterMapper.selectByMap(map);
        list.forEach(System.out::println);*/

        // 查询所有实体
//        SELECT chapter_id,chapter_name,pre_chapter_id,course_id FROM chapter
        List<Chapter> list = chapterMapper.selectList(null);
        list.forEach(System.out::println);


    }
    @Resource
    UserMapper userMapper;

//    @Test
//    void test(){
////        select user_id,user_name,account,password from user where user_id = ?
//        Map<String,Object> map = userMapper.selectMapById("2022");
//        System.out.println(map);
//
//    }
}
