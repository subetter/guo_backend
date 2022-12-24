package com.guo_backend;

import com.guo_backend.domain.Chapter;
import com.guo_backend.service.ChapterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisPlusServiceTest {

    @Autowired
    private ChapterService chapterService;

    @Test
    public void testGetCount() {
//        查询总记录数
//        SELECT COUNT( * ) FROM chapter
        long count = chapterService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testInsertMore() {
//        Preparing: INSERT INTO chapter ( chapter_id, chapter_name, pre_chapter_id ) VALUES ( ?, ?, ? )
        List<Chapter> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Chapter chapter = new Chapter();
            chapter.setChapterId(String.valueOf(i + 6));
            chapter.setPreChapterId(String.valueOf(i));
            chapter.setChapterName("tchapter" + i);
            list.add(chapter);
        }

        boolean flag = chapterService.saveBatch(list);
        System.out.println("操作是否成功：" + flag);
    }

}
