package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.Chapter;
import com.guo_backend.mapper.ChapterMapper;
import com.guo_backend.service.ChapterService;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【chapter】的数据库操作Service实现
* @createDate 2022-12-04 23:23:09
*/
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
    implements ChapterService {

}




