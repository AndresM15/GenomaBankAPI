package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Representa la clave primaria compuesta (PK) de la tabla Cromosoma.
 * Combina el ID del Genoma y el Nombre del Cromosoma.
 */
@Embeddable // Indica que esta clase se usará como parte de otra entidad
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CromosomaId implements Serializable {

    // Debe coincidir con el nombre de la columna en el SQL
    @Column(name = "ID_Genoma", nullable = false, length = 100)
    private String idGenoma;

    // Debe coincidir con el nombre de la columna en el SQL
    @Column(name = "Nombre_Cromosoma", nullable = false, length = 100)
    private String nombreCromosoma;
}