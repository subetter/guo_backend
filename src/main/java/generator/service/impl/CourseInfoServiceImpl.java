package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.CourseInfo;
import generator.service.CourseInfoService;
import generator.mapper.CourseInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【course_info】的数据库操作Service实现
* @createDate 2022-12-04 23:24:01
*/
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
    implements CourseInfoService{

}




