package com.guo_backend.domain.dto;

import com.guo_backend.domain.Chapter;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Builder
public class ChapterDto {
    private List<Chapter> result;
}
