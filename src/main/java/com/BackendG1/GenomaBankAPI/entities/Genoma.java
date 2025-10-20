package com.BackendG1.GenomaBankAPI.entities;

import com.BackendG1.GenomaBankAPI.entities.Especie; // (Si aún no está implícito)
import com.BackendG1.GenomaBankAPI.entities.Cromosoma; // (Si aún no está implícito)
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Genoma")
public class Genoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String version;

    // Relación N:1 con Especie (un genoma pertenece a una especie)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especie_id", nullable = false) // FK: especie_id
    private Especie especie;

    // Relación 1:N con Cromosoma (un genoma tiene muchos cromosomas)
    @OneToMany(mappedBy = "genoma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cromosoma> cromosomas;

}
