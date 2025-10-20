package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Genes {
    enum OrientacionGen{
        MAS("+"),
        MENOS("-");

        private final String simbolo;

        OrientacionGen(String simbolo){
            this.simbolo = simbolo;
        }

        public String getSimbolo(){
            return simbolo;
        }
    }

    @Entity
    @Table(name = "gen")
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class genes {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "simbolo" , length = 100 , nullable = false)
        private String simbolo;

        @Column(name = "posicion_final" , nullable = false)
        private Integer posicion_final;

        @Column (name = "posicion_inicial" , nullable = false)
        private Integer posicion_inicial;

        @Enumerated(EnumType.STRING)
        @Column(name = "orientacion" , length = 1 , nullable = false)
        private OrientacionGen orientacion;

        @Lob
        @Column(name = "secuencia_adn" , nullable = false)
        private String secuencia_adn;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "cromosoma_Id",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_Gen_Cromosoma")

        )
        private Cromosoma cromosoma;

    }
}
