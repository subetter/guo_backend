package com.guo_backend.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
public class Comment {
    private String commentId;
    private Date commentTime;
    private String commentContent;
    //    private String user_id;
    private String username;
}
