package com.BackendG1.GenomaBankAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CromosomaDTO {
    // El nombre del cromosoma, parte de la clave compuesta
    private String nombreCromosoma;

    private Long longitudParesBase;

    // La secuencia de ADN no se suele devolver en listados por su gran tamaño.
    // Se incluirá en la respuesta del GET específico, pero es opcional aquí.
    private String secuenciaADN;

    // El ID del genoma al que pertenece, parte de la clave compuesta
    private Long idGenoma;
}
