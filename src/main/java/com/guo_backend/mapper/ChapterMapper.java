package com.guo_backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.domain.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author fu
* @description 针对表【chapter】的数据库操作Mapper
* @createDate 2022-12-04 23:23:09
* @Entity generator.domain.Chapter
*/
@Repository
public interface ChapterMapper extends BaseMapper<Chapter> {

    /**
     * 通过course_id查询章节信息，并分页
     * @param page MyBatisPlus 所提供的分页对象，必须位于第一个参数的位置
     * @param course_id
     * @return
     */
    Page<Chapter> selectPageVo(@Param("page") Page<Chapter> page,@Param("course_id") String course_id);
}




