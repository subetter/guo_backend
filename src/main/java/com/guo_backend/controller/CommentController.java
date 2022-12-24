package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.Comments;
import com.guo_backend.domain.User;
import com.guo_backend.service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Component
@Tag(name = "评论控制器")
public class CommentController {

    @Resource
    CommentsService commentsService;

    public CommentController(CommentsService commentsService){
        this.commentsService = commentsService;
    }

    // 用户发表评论
    @Operation(summary = "用户发表评论")
    @PostMapping("/comment/create")
    public BaseResponse<Comments> createComments(@AuthenticationPrincipal User user, @RequestBody Comments comments){
        System.out.println("User:"+user+"\n comments:"+comments);
        return ResultUtils.success(commentsService.createComments(comments,user));
    }

}
