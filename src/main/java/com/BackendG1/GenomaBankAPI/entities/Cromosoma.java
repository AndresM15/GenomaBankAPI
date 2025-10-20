package com.BackendG1.GenomaBankAPI.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "Cromosoma")
public class Cromosoma {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String nombre;
    @Column(name = "longitud_pb", nullable = false) private Long longitudPb;
    @Lob @Column(name = "secuencia_adn", nullable = false, columnDefinition = "LONGTEXT") private String secuenciaAdn;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "genoma_id", nullable = false) private Genoma genoma;
    @OneToMany(mappedBy = "cromosoma", cascade = CascadeType.ALL, orphanRemoval = true) private List<Gen> gens;
}