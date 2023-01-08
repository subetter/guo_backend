package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.Comment;
import com.guo_backend.domain.dto.CommentsDto;
import com.guo_backend.domain.dto.ReplyDto;
import com.guo_backend.mapper.CommentsMapper;
import com.guo_backend.mapper.UserMapper;
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

    // 发布评论
    @Override
    public Comments dispalyComment(Comments comments){
        try{
            Comments newcomments = new Comments();
            String uuid = UUID.randomUUID().toString();
            newcomments.setCommentId(uuid);
            newcomments.setUserId(comments.getUserId());
            newcomments.setUsername(comments.getUsername());
            newcomments.setChapterId(comments.getChapterId());
            newcomments.setCommentContent(comments.getCommentContent());
            newcomments.setRootId("0");
            newcomments.setPreId("0");
            newcomments.setCommentTime(new Date());
            newcomments.setStatus(0);
            commentsMapper.insert(newcomments);
            return newcomments;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    回复评论
    @Override
    public Comments createComments(Comments comments) {
        try{
            Comments newcomments = new Comments();
            String uuid = UUID.randomUUID().toString();
            newcomments.setCommentId(uuid);
            newcomments.setUserId(comments.getUserId());
            newcomments.setUsername(comments.getUsername());
            newcomments.setChapterId(comments.getChapterId());
            newcomments.setCommentContent(comments.getCommentContent());
            newcomments.setRootId(comments.getRootId());
            newcomments.setPreId(comments.getPreId());
            newcomments.setCommentTime(new Date());
            newcomments.setStatus(0);
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

    @Resource
    UserMapper userMapper;
    @Override
    public List<ReplyDto> getreply(String commentId) {
        try{
            QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("root_id",commentId).eq("status",1);
//            ReplyDto replyDto = new ReplyDto();
            // list 是所有的回复
            List<ReplyDto> reslist = new ArrayList<>();
            List<Comments> list = commentsMapper.selectList(queryWrapper);
            for(Comments reply : list){
                ReplyDto replyDto = new ReplyDto();
                // 遍历每一条评论，根据userid查出avatar
                QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("user_id",reply.getUserId());
                User user = userMapper.selectOne(queryWrapper1);
                replyDto.setAvatar(user.getAvatar());
                replyDto.setCommentId(reply.getCommentId());
                replyDto.setCommentContent(reply.getCommentContent());
                replyDto.setCommentTime(reply.getCommentTime());
                replyDto.setChapterId(reply.getChapterId());
                replyDto.setPreId(reply.getPreId());
                replyDto.setStatus(reply.getStatus());
                replyDto.setUsername(reply.getUsername());
                replyDto.setRootId(reply.getRootId());
                replyDto.setUserId(reply.getUserId());
                reslist.add(replyDto);
            }
            return reslist;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public CommentsDto getRreply(String commentId) {
//        try{
//            QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("pre_id",commentId);
//            List<Comments> list = commentsMapper.selectList(queryWrapper);
//            return CommentsDto.builder()
//                    .results(list)
//                    .build();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

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

    @Override
    public Boolean updateCommentStatus(Comments comments) {
        boolean flag;
        QueryWrapper<Comments> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("comment_id",comments.getCommentId());
        Comments comments1=commentsMapper.selectOne(queryWrapper);
        comments1.setStatus(comments.getStatus());
        UpdateWrapper<Comments> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("status",comments1.getStatus());
        int res=commentsMapper.update(comments1,queryWrapper);
        if(res>0){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }

}




