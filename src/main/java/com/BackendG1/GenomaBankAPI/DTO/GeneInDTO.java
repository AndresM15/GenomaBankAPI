package com.BackendG1.GenomaBankAPI.DTO;

import lombok.Data;

@Data
public class GeneInDTO {
    private String symbol;
    private Integer startPos;
    private Integer endPos;
    private String strand;
    private String sequence;
    private Long cromosomaId;
}