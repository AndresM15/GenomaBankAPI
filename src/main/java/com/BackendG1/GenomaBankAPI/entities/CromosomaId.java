package com.BackendG1.GenomaBankAPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Representa la clave primaria compuesta para la entidad Cromosoma.
 * Es necesaria porque la PK en la base de datos está formada por más de una columna.
 */
@Embeddable // Indica que esta clase se puede incrustar en otra entidad.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CromosomaId implements Serializable {

    @Column(name = "ID_Genoma")
    private String idGenoma;

    @Column(name = "Nombre_Cromosoma")
    private String nombreCromosoma;
}

