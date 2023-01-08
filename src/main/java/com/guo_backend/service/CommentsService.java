package com.guo_backend.service;

import com.guo_backend.domain.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CommentsDto;
import com.guo_backend.domain.dto.ReplyDto;

import java.util.List;

/**
* @author fu
* @description 针对表【comments】的数据库操作Service
* @createDate 2022-12-04 23:23:51
*/
public interface CommentsService extends IService<Comments> {

    // 发布评论
    Comments dispalyComment(Comments comments);

    Comments createComments(Comments comments);
    CommentsDto getCommentList(String chapterId, String userId);
    List<ReplyDto> getreply1(String commentId);
//    CommentsDto getRreply(String commentId);
    CommentsDto getreply(String commentId);
    CommentsDto getRreply(String commentId);
    CommentsDto getCommentUnchecked();
    Boolean updateCommentStatus(Comments comments);
}
