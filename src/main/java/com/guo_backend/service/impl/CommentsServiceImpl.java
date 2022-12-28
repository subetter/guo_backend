package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.Comment;
import com.guo_backend.domain.dto.CommentsDto;
import com.guo_backend.mapper.CommentsMapper;
import com.guo_backend.service.CommentsService;
import com.guo_backend.domain.Comments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @author fu
* @description 针对表【comments】的数据库操作Service实现
* @createDate 2022-12-04 23:23:51
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService {

    @Resource
    CommentsMapper commentsMapper;

    @Override
    public Comments createComments(Comments comments, User user) {
        try{
            Comments newcomments = new Comments();
            String uuid = UUID.randomUUID().toString();
            newcomments.setCommentId(uuid);
            newcomments.setUserId(user.getUserId());
            newcomments.setUsername(user.getUsername());
            newcomments.setChapterId(comments.getChapterId());
            newcomments.setCommentContent(comments.getCommentContent());
            newcomments.setRootId(comments.getRootId());
            newcomments.setPreId(comments.getPreId());
            newcomments.setCommentTime(new Date());
            commentsMapper.insert(newcomments);
            return newcomments;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommentsDto getCommentList(String chapterId, String userId) {
        try {
            QueryWrapper<Comments> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("chapter_id",chapterId)
                    .eq("root_id","0");
            List<Comments> commentsList=commentsMapper.selectList(queryWrapper);
            return CommentsDto.builder()
                    .results(commentsList)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommentsDto getreply(String commentId) {
        try{
            QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("root_id",commentId);
            List<Comments> list = commentsMapper.selectList(queryWrapper);
            return CommentsDto.builder()
                    .results(list)
                    .build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommentsDto getRreply(String commentId) {
        try{
            QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pre_id",commentId);
            List<Comments> list = commentsMapper.selectList(queryWrapper);
            return CommentsDto.builder()
                    .results(list)
                    .build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommentsDto getCommentUnchecked() {
        //用来存储结果
        List<Comment> res=new ArrayList<>();
        //查询未审核的评论列表
        QueryWrapper<Comments> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",0);
        List<Comments> list=commentsMapper.selectList(queryWrapper);
        //循环遍历列表
        for(Comments cm :list){
            //遍历一次生成一个对象
            Comment cm1=new Comment();
            QueryWrapper<Comments> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("comment_id",cm.getCommentId());
            Comments comments=commentsMapper.selectOne(queryWrapper1);
            //根据id查出对应的commentTime，
            cm1.setCommentId(comments.getCommentId());
            cm1.setCommentTime(comments.getCommentTime());
            cm1.setCommentContent(comments.getCommentContent());
            cm1.setUsername(comments.getUsername());
            res.add(cm1);
        }
        return CommentsDto.builder()
                .res(res)
                .build();
    }

}




