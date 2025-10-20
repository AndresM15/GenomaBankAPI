package com.BackendG1.GenomaBankAPI.entities;


import com.BackendG1.GenomaBankAPI.enums.FunctionCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FuncionBiologica")
public class FuncionBiologica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre_descriptivo", nullable = false) // Mapeo de snake_case
    private String nombreDescriptivo;

    @Enumerated(EnumType.STRING) // Guarda el enum como String ("BP", "MF", "CC")
    @Column(nullable = false)
    private FunctionCategory categoria;

    // Relación M:M con Gen a través de GenFuncion
    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL)
    private Set<GenFuncion> genes;

}
