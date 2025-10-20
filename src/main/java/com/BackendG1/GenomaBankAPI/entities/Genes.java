package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Gen")
public class Genes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "simbolo", nullable = false)
    private String symbol;

    @Column(name = "posicion_inicio", nullable = false)
    private Integer startPos;

    @Column(name = "posicion_final", nullable = false)
    private Integer endPos;

    @Column(name = "orientacion", nullable = false)
    private String strand;

    @Column(name = "secuencia_adn", columnDefinition = "LONGTEXT", nullable = false)
    private String sequence;

    @ManyToOne
    @JoinColumn(name = "cromosoma_id", nullable = false)
    private Chromosomes cromosoma;
}