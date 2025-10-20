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
@Table(name = "Cromosoma") // Coincide con el nombre de la tabla SQL
public class Cromosoma {

    // 1. USA LA CLAVE COMPUESTA
    @EmbeddedId
    private CromosomaId id;

    // 2. MAPEA LAS OTRAS COLUMNAS (con nombres SQL correctos)
    @Column(name = "Longitud_Pares_Base", nullable = false)
    private Long longitudParesBase; // Tu DTO y Service usan "longitudParesBase"

    @Lob
    @Column(name = "Secuencia_ADN", nullable = false, columnDefinition = "LONGTEXT")
    private String secuenciaADN; // Tu DTO y Service usan "secuenciaADN"

    // 3. MAPEA LA RELACIÓN (que también es parte de la PK)

    // @MapsId le dice a JPA que el campo "idGenoma" de nuestro @EmbeddedId
    // es también la clave foránea para esta relación @ManyToOne.
    @MapsId("idGenoma")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Genoma", nullable = false, insertable = false, updatable = false)
    private Genoma genoma;

    // 4. RELACIÓN CON GEN (Esto asume que Gen también usará clave compuesta)
    @OneToMany(mappedBy = "cromosoma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genoma> gens;
}