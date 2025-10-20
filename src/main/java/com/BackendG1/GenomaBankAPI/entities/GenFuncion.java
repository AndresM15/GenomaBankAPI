package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Gen_Funcion")
@Data
public class GenFuncion {

    @EmbeddedId
    private GenFuncionId id;

    @ManyToOne
    @MapsId("genId")
    @JoinColumns({
            @JoinColumn(name = "ID_Genoma", referencedColumnName = "ID_Genoma"),
            @JoinColumn(name = "Nombre_Cromosoma", referencedColumnName = "Nombre_Cromosoma"),
            @JoinColumn(name = "Simbolo_Gen", referencedColumnName = "Simbolo_Gen")
    })
    private Genoma gen;

    @ManyToOne
    @MapsId("codigoFuncion")
    @JoinColumn(name = "Codigo_Funcion")
    private FuncionBiologica funcion;

    @Column(name = "Evidencia", length = 100)
    private String evidencia;
}
