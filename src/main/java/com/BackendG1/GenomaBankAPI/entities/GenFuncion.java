package com.BackendG1.GenomaBankAPI.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "Gen_Funcion")
public class GenFuncion {
    @EmbeddedId private GenFuncionId id;
    private String evidencia;
    @ManyToOne(fetch = FetchType.LAZY) @MapsId("genId") @JoinColumn(name = "gen_id") private Gen gen;
    @ManyToOne(fetch = FetchType.LAZY) @MapsId("funcionId") @JoinColumn(name = "funcion_id") private FuncionBiologica funcion;
}