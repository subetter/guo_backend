package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Log;
import generator.service.LogService;
import generator.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【log】的数据库操作Service实现
* @createDate 2022-12-04 23:24:39
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

}




