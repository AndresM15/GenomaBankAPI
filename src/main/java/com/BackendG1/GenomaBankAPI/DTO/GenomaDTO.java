package com.BackendG1.GenomaBankAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenomaDTO {
    private String idGenoma;
    private String versionEnsamblaje;
    private String nombreCientificoEspecie;
}
