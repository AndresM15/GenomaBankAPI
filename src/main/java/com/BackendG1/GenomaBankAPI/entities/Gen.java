package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "Gen")
public class Gen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String simbolo;
    @Column(name = "posicion_inicio", nullable = false) private Integer posicionInicio;
    @Column(name = "posicion_final", nullable = false) private Integer posicionFinal;
    @Column(nullable = false) private String orientacion;

    // <<< SOLUCIÓN AQUÍ >>>
    // Aplicamos la misma corrección a esta entidad.
    @Lob
    @Column(name = "secuencia_adn", nullable = false, columnDefinition = "LONGTEXT")
    private String secuenciaAdn;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cromosoma_id", nullable = false) private Cromosoma cromosoma;
    @OneToMany(mappedBy = "gen", cascade = CascadeType.ALL) private Set<GenFuncion> funciones;
}