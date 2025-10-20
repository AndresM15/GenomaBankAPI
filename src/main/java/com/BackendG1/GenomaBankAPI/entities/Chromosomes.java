package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cromosoma")
@Data
@NoArgsConstructor
public class Chromosomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "longitud_pb", nullable = false)
    private Long longitudPb; // Mapeado a BIGINT

    @Column(name = "secuencia_adn", columnDefinition = "LONGTEXT", nullable = false)
    private String secuenciaAdn;

    @Column(name = "genoma_id", nullable = false)
    private Long genomaId;
}

