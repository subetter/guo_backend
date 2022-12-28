package com.guo_backend.domain.dto;

import com.guo_backend.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
@Builder
public class UserList {
    private List<User> result;
}
