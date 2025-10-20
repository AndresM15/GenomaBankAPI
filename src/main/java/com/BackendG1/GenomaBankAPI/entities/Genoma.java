package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
// ... otros imports

@Entity
@Table(name = "Genoma") // Coincide con el SQL
@Data
public class Genoma {

    @Id // Es un ID, pero NO es autogenerado
    @Column(name = "ID_Genoma", length = 100) // Coincide con el nombre de la columna SQL
    private String id; // <-- CAMBIADO A STRING

    @Column(name = "Version_Ensamblaje", nullable = false) // Coincide con el SQL
    private String version; // <-- Nombre de campo corregido

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Nombre_Cientifico", nullable = false) // Coincide con el SQL
    private Especie especie;

    @OneToMany(mappedBy = "genoma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cromosoma> cromosomas;

    // Asegúrate de tener constructores vacíos y getters/setters (Lombok @Data lo hace)
}