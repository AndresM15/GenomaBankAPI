package com.BackendG1.GenomaBankAPI.DTO;

import lombok.Data;

@Data
public class FunctionOutDTO {
    private Long id;
    private String code;
    private String name;
    private String category;
}