package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.Comments;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CommentsDto;
import com.guo_backend.service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 用户回复评论
    @Operation(summary = "回复评论")
    @PostMapping("/reply")
    public BaseResponse<Comments> reply(@AuthenticationPrincipal User user, @RequestBody Comments comments){
//        System.out.println("User:"+user+"\n comments:"+comments);
        return ResultUtils.success(commentsService.createComments(comments,user));
    }

    @Operation(summary = "获取评论列表")
    @PostMapping("/student/getcomments")
    public BaseResponse<CommentsDto> getCommentList(@RequestParam String chapterId, @RequestParam String userId){
        return ResultUtils.success(commentsService.getCommentList(chapterId,userId));
    }

//    @Operation(summary = "获取评论的所有回复")
//    @GetMapping("/allreply")
//    public BaseResponse<CommentsDto> getReply(@RequestParam String commentId){
//        return ResultUtils.success(commentsService.getreply(commentId));
//    }

    @Operation(summary = "获取回复评论的回复")
    @GetMapping("/specify/reply")
    public BaseResponse<CommentsDto> getRReply(@RequestParam String commentId){
        return ResultUtils.success(commentsService.getRreply(commentId));
    }
    @Operation(summary = "获取未审核的评论")
    @GetMapping("/get/comments")
    public BaseResponse<CommentsDto> getCommentUnchecked(){
        return ResultUtils.success(commentsService.getCommentUnchecked());
    }
    @Operation(summary = "修改已经审核过的评论状态")
    @PostMapping(value = "/update/status")
    public BaseResponse<Boolean> updateCommentStatus(@RequestBody Comments comments){
        return ResultUtils.success(commentsService.updateCommentStatus(comments));
    }
}
