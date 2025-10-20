package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenFuncionId implements Serializable {

    @Column(name = "Codigo_Funcion")
    private String codigoFuncion;

    // Campos de la clave de Gen
    private GenId genId;
}
