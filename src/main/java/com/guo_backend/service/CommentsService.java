package com.guo_backend.service;

import com.guo_backend.domain.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CommentsDto;

/**
* @author fu
* @description 针对表【comments】的数据库操作Service
* @createDate 2022-12-04 23:23:51
*/
public interface CommentsService extends IService<Comments> {

    Comments createComments(Comments comments, User user);
    CommentsDto getCommentList(String chapterId, String userId);
    CommentsDto getreply(String commentId);
    CommentsDto getRreply(String commentId);
    CommentsDto getCommentUnchecked();
}
