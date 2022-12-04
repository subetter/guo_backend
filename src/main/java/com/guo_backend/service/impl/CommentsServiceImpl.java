package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.mapper.CommentsMapper;
import com.guo_backend.service.CommentsService;
import com.guo_backend.domain.Comments;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【comments】的数据库操作Service实现
* @createDate 2022-12-04 23:23:51
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService {

}




