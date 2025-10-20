package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Representa un Genoma en la base de datos.
 * Cada genoma pertenece a una Especie y contiene múltiples Cromosomas.
 */
@Entity
@Table(name = "Genoma")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genoma {

    /**
     * Identificador único del genoma (clave primaria autoincremental).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Versión o ensamblaje del genoma (ej. "GRCh38.p13").
     */
    @Column(name = "version", nullable = false)
    private String version;

    /**
     * Relación Muchos-a-Uno con la entidad Especie.
     * Un genoma pertenece a una sola especie.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    /**
     * Relación Uno-a-Muchos con la entidad Cromosoma.
     * Un genoma puede contener una lista de cromosomas.
     * mappedBy indica que la entidad Cromosoma es la dueña de la relación.
     */
    @OneToMany(mappedBy = "genoma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cromosoma> cromosomas;
}