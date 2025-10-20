package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "Genoma")
public class Genoma {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String version;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "especie_id", nullable = false) private Especie especie;
    @OneToMany(mappedBy = "genoma", cascade = CascadeType.ALL, orphanRemoval = true) private List<Cromosoma> cromosomas;
}