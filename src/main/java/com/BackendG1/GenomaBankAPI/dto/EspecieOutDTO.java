package com.BackendG1.GenomaBankAPI.dto;

import lombok.Data;

@Data
public class EspecieOutDTO {
    private Long id;
    private String nombreCientifico;
    private String nombreComun;
}