package com.BackendG1.GenomaBankAPI.entities;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data // Genera getters, setters, toString, y los necesarios equals/hashCode
@NoArgsConstructor // Constructor sin argumentos (requerido por JPA)
@AllArgsConstructor // Constructor con todos los argumentos (útil para pruebas)
public class GenFuncionId implements Serializable {

    // Mapea a la clave foránea 'gen_id' en la base de datos
    private Long genId;

    // Mapea a la clave foránea 'funcion_id' en la base de datos
    private Long funcionId;


}