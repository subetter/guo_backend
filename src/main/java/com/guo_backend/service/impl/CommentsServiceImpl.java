package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CommentsDto;
import com.guo_backend.mapper.CommentsMapper;
import com.guo_backend.service.CommentsService;
import com.guo_backend.domain.Comments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
                    .eq("user_id",userId);
            List<Comments> commentsList=commentsMapper.selectList(queryWrapper);
            return CommentsDto.builder()
                    .results(commentsList)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}




