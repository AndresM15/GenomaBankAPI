package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "Especie")
public class Especie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "nombre_cientifico", nullable = false, unique = true) private String nombreCientifico;
    @Column(name = "nombre_comun") private String nombreComun;
    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true) private List<Genoma> genomas;
}