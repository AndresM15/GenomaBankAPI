package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Genoma")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genoma {

    @Id
    @Column(name = "ID_Genoma")
    private String idGenoma;

    @Column(name = "Version_Ensamblaje", nullable = false)
    private String versionEnsamblaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Nombre_Cientifico", nullable = false)
    private Especie especie;

    @OneToMany(mappedBy = "genoma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cromosoma> cromosomas;
}
