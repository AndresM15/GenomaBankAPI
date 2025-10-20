package com.BackendG1.GenomaBankAPI.dto;

import lombok.Data;
@Data
public class CromosomaDTO {
    private Long id;
    private String nombre;
    private Long longitudPb;
    private String secuenciaAdn; // Opcional, para respuestas detalladas
    private Long genomaId;
}