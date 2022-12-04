package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.Log;
import com.guo_backend.mapper.LogMapper;
import com.guo_backend.service.LogService;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【log】的数据库操作Service实现
* @createDate 2022-12-04 23:24:39
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService {

}




