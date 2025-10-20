package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "cromosoma")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cromosoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="nombre" , length=100 ,  nullable=false)
    private String nombre;

    @Column(name = "longitud_pb" , length=100 ,  nullable=false)
    private Long longitud_pb;

    @Lob
    @Column(name="secuencia_adn" , nullable=false)
    private String secuencia_adn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genoma_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Cromosoma_Genoma"))

    private Genoma genoma;




}
