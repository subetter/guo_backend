package com.guo_backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.domain.Chapter;
import com.guo_backend.mapper.ChapterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    void tesPage(){
        Page<Chapter> page = new Page<>(1,3);
        chapterMapper.selectPage(page,null);
//        获取当前页的数据 返回一个list集合
        System.out.println(page.getRecords());
//        获取当前页的页码
        System.out.println(page.getCurrent());
//        获取每页显示的条数
        System.out.println(page.getSize());
//         获取总页数
        System.out.println(page.getPages());
//        获取总记录数
        System.out.println(page.getTotal());
//     判断是否有上一页，下一页
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

    @Test
    void testPageVo(){
        Page<Chapter> page = new Page<>();
        chapterMapper.selectPageVo(page,"1");
        System.out.println(page.getRecords());
//        获取当前页的页码
        System.out.println(page.getCurrent());
//        获取每页显示的条数
        System.out.println(page.getSize());
//         获取总页数
        System.out.println(page.getPages());
//        获取总记录数
        System.out.println(page.getTotal());
//     判断是否有上一页，下一页
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }
}
