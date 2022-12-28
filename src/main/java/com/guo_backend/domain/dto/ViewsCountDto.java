package com.guo_backend.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class ViewsCountDto {
    private List<ViewsCount> res;
}
