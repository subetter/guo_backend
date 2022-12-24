package com.guo_backend.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class CourseRequest {
    private String search;
    private String typeId;
    private Long currentPage;
}
