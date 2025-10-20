package com.BackendG1.GenomaBankAPI.DTO;

import lombok.Data;

@Data
public class UpdateGeneDTO {
    private String symbol;
    private Integer startPos;
    private Integer endPos;
    private String strand;
    private String sequence;
}

