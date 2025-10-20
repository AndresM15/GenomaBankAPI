package com.BackendG1.GenomaBankAPI.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Gen_Funcion")
public class GenFuncion {
    // Clave primaria compuesta (GenId y FuncionId)
    @EmbeddedId
    private GenFuncionId id;

    @Column
    private String evidencia; // Opcional: "experimental", "computacional"

    // Relación N:1 con Gen
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("genId") // Mapea al campo 'genId' de GenFuncionId
    @JoinColumn(name = "gen_id")
    private Gen gen;

    // Relación N:1 con FuncionBiologica
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("funcionId") // Mapea al campo 'funcionId' de GenFuncionId
    @JoinColumn(name = "funcion_id")
    private FuncionBiologica funcion;

}
