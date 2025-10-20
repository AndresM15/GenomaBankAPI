package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Especie")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_cientifico", nullable = false, unique = true) // Mapeo de snake_case
    private String nombreCientifico;
    @Column(name = "nombre_comun") // Mapeo de snake_case
    private String nombreComun;

    // Relación 1:N con Genoma (una especie tiene muchos genomas)
    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genoma> genomas;

}
