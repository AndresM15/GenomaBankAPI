package com.BackendG1.GenomaBankAPI.DTO;

import lombok.Data;

@Data
public class ChromosomeUpdateDTO {
    private String nombre;
    private Long longitudPb;
    private String secuenciaAdn;
    private Long genomaId;
}
