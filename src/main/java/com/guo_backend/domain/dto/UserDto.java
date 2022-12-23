package com.guo_backend.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author fu
 * @Date 2022 12 23 16 18
 **/
@Getter
@Setter
@Data
@Builder
public class UserDto {
    private String userId;
    private String account;
    private Integer roleId;
    private String avatar;
    private String userName;
    private Date createTime;
}
