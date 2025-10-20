package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "FuncionBiologica")
public class Functions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "nombre_descriptivo", nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;
}

