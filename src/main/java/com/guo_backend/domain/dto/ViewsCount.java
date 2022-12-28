package com.guo_backend.domain.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ViewsCount {
    private String courseName;
    private String chapterName;
    private Integer count;
}
