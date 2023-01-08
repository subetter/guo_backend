package com.guo_backend.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo_backend.domain.CourseInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class CourseinfoDto {
    private Page<CourseInfo> results;
}
