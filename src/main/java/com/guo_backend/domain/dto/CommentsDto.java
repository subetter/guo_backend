package com.guo_backend.domain.dto;

import com.guo_backend.domain.Comments;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Builder
public class CommentsDto {
//    private String comment_id;
//    private Date comment_time;
//    private String comment_content;
////    private String user_id;
//    private String username;
    private List<Comments> results;
    private List<Comment> res;
}
