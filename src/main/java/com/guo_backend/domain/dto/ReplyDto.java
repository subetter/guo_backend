package com.guo_backend.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDto {
    private String commentId;
    private Date commentTime;
    private String commentContent;
    private String userId;
    private String username;
    private String chapterId;
    private Integer status;
    private String rootId;
    private String preId;
    private String avatar;
}
