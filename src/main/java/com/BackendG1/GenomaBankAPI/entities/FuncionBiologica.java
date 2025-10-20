package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Funcion_Biologica")
@Data
public class FuncionBiologica {

    @Id
    @Column(name = "Codigo_Funcion", length = 50)
    private String codigoFuncion;

    @Column(name = "Nombre_Descriptivo", nullable = false)
    private String nombreDescriptivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Categoria", nullable = false)
    private CategoriaFuncion categoria;

    // Enum para manejar las categorías de forma segura
    public enum CategoriaFuncion {
        BP, MF, CC
    }
}
