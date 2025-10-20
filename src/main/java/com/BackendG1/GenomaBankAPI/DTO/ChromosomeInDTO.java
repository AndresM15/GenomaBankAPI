package com.BackendG1.GenomaBankAPI.DTO;

import lombok.Data;

@Data
public class ChromosomeInDTO {
    private String nombre;
    private Long longitudPb;
    private String secuenciaAdn;
    private Long genomaId;
}
