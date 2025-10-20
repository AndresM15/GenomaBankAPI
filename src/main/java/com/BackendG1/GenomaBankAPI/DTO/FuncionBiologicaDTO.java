package com.BackendG1.GenomaBankAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionBiologicaDTO {
    private String codigoFuncion;
    private String nombreDescriptivo;
    private String categoria; // BP, MF, o CC
}
