package com.BackendG1.GenomaBankAPI.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO unificado para manejar la entrada y salida de datos de Genoma.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenomaDTO {
    private String id;
    private String version;
    private String especieId; // FK a Especie, se usará para la entrada de datos
    private String nombreCientificoEspecie; // Campo extra para mostrar info en la salida
}


