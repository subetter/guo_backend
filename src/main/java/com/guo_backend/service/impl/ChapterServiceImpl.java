package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.Chapter;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author fu
 * @description 针对表【chapter】的数据库操作Service实现
 * @createDate 2022-12-04 23:23:09
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
        implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public Chapter getChapterInfo( String courseId ) {
        try {
            QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_id", courseId);
            List<Chapter> chapterList = chapterMapper.selectList(queryWrapper);
            return (Chapter) chapterList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 新增大章节
    @Override
    public Chapter addChapter( Chapter chapter ) {
        try {
            Chapter nchapter = new Chapter();
            nchapter.setChapterName(chapter.getChapterName());
            nchapter.setCourseId(chapter.getCourseId());
            nchapter.setPreChapterId(chapter.getPreChapterId());
            nchapter.setRootChapterId("0");
            nchapter.setIsDeleted(0);
            String uuid = UUID.randomUUID().toString();
            nchapter.setChapterId(uuid);
            chapterMapper.insert(nchapter);
            return nchapter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    新增小章节
    @Override
    public Chapter addLittleChapter( Chapter chapter ) {
        try {
            Chapter nchapter = new Chapter();
            nchapter.setChapterName(chapter.getChapterName());
            nchapter.setCourseId(chapter.getCourseId());
            nchapter.setRootChapterId(chapter.getRootChapterId());
            String uuid = UUID.randomUUID().toString();
            nchapter.setChapterId(uuid);
            if (chapter.getPreChapterId() == null) {
                nchapter.setPreChapterId(uuid);
            } else {
                nchapter.setPreChapterId(chapter.getPreChapterId());
            }
            nchapter.setIsDeleted(0);
            chapterMapper.insert(nchapter);
            return nchapter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateChapter( Chapter chapter ) {
        try {
            QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("chapter_id", chapter.getChapterId());
            Chapter chapter1 = chapterMapper.selectOne(queryWrapper);
            chapter1.setChapterName(chapter.getChapterName());
            UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("chapter_name", chapter1.getChapterName());
            int res = chapterMapper.update(chapter1, queryWrapper);
            if (res > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBChapter( List<String> chapterIds ) {
        boolean flag = true;
        List<Chapter> chapterList = this.listByIds(chapterIds);
        for (Chapter chapter : chapterList) {
            if ("0".equals(chapter.getRootChapterId())) {
                String preId = chapter.getPreChapterId();
                QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
                chapterQueryWrapper.eq("pre_chapter_id", chapter.getChapterId())
                        .eq("root_chapter_id", "0");
                Chapter chapter1 = this.getOne(query());
                chapter1.setPreChapterId(preId);
                boolean flag3 = this.updateById(chapter1);
                QueryWrapper<Chapter> chapterQueryWrapper1 = new QueryWrapper<>();
                chapterQueryWrapper1.eq("root_chapter_id", chapter);
                boolean flag2 = this.remove(chapterQueryWrapper1);
                if (!flag2 || !flag3) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean deleteSChapter( List<String> chapterIds ) {
        Integer quantity = chapterIds.size();
        Chapter chapter = this.getById(chapterIds.get(0));
        String firstPre = chapter.getPreChapterId();
        Chapter chapter2 = this.getById(chapterIds.get(quantity - 1));
        String lastId = chapter2.getChapterId();
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pre_chapter_id", lastId);
        Chapter chapter3 = this.getOne(queryWrapper);
        chapter3.setPreChapterId(firstPre);
        boolean flag = this.updateById(chapter3);
        return flag;
    }

}




