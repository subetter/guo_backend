package com.guo_backend.domain.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Calc {
    private String typeName;
    private Long quantity;
}
