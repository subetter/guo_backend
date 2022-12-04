package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Chapter;
import generator.service.ChapterService;
import generator.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【chapter】的数据库操作Service实现
* @createDate 2022-12-04 23:23:09
*/
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
    implements ChapterService{

}




