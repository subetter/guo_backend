package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.Chapter;
import com.guo_backend.domain.CourseInfo;
import com.guo_backend.domain.CourseResourse;
import com.guo_backend.domain.dto.ViewsCount;
import com.guo_backend.domain.dto.ViewsCountDto;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.mapper.CourseInfoMapper;
import com.guo_backend.mapper.CourseResourseMapper;
import com.guo_backend.service.CourseResourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fu
 * @description 针对表【course_resourse】的数据库操作Service实现
 * @createDate 2022-12-04 23:24:11
 */
@Service
public class CourseResourseServiceImpl extends ServiceImpl<CourseResourseMapper, CourseResourse>
        implements CourseResourseService {
    @Resource
    private CourseResourseMapper courseResourseMapper;

    @Override
    public CourseResourse getPdf(String chapterId) {
        QueryWrapper<CourseResourse> queryWrapper = Wrappers.<CourseResourse>query()
                .eq("chapter_id", chapterId);
        return courseResourseMapper.selectOne(queryWrapper);
    }

    // 查询课程内的第一个视频
    @Override
    public CourseResourse getFirstVideo(String courseId) {
        try {
            QueryWrapper<CourseResourse> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_first", 1)
                    .eq("course_id", courseId);
            return courseResourseMapper.selectOne(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CourseResourse getvideo(String chapterId, String courseId) {
        QueryWrapper<CourseResourse> queryWrapper = Wrappers.<CourseResourse>query()
                .eq("course_id", courseId)
                .eq("chapter_id", chapterId);
        return courseResourseMapper.selectOne(queryWrapper);
    }

    @Resource
    CourseInfoMapper courseInfoMapper;

    @Resource
    ChapterMapper chapterMapper;


    // 观看次数统计
    @Override
    public ViewsCountDto viewscount() {
        try {
            // 存储最终结果
            List<ViewsCount> res = new ArrayList<>();
            List<CourseResourse> list = courseResourseMapper.selectList(null);

            for (CourseResourse cr : list) {
                QueryWrapper<CourseInfo> queryWrapper1 = new QueryWrapper<>();
                QueryWrapper<Chapter> queryWrapper2 = new QueryWrapper<>();
                System.out.println(cr.toString());
                // 遍历列表，每循环一次生成一个ViewsCount item对象
                ViewsCount vc = new ViewsCount();
                // 查出courseid对应的coursename,item.setcoursename
                queryWrapper1.eq("course_id", cr.getCourseId());
                CourseInfo courseInfo = courseInfoMapper.selectOne(queryWrapper1);
                vc.setCourseName(courseInfo.getCourseName());
                //查出chapterid对应的chaptername, item.setchaptername
                queryWrapper2.eq("chapter_id", cr.getChapterId());
                Chapter chapter = chapterMapper.selectOne(queryWrapper2);
                vc.setChapterName(chapter.getChapterName());
                // 查出count, item.setcount
                vc.setCount(cr.getCount());
                res.add(vc);
            }
            return ViewsCountDto.builder()
                    .res(res)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}




