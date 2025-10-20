package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Genoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenomaRepository extends JpaRepository<Genoma, Long> {

    /**
     * <<< SOLUCIÓN >>>
     * Este es el método correcto que busca genomas por el ID de la entidad Especie a la que pertenecen.
     * Spring Data JPA entiende automáticamente el nombre "findByEspecieId" y crea la consulta
     * SQL correcta (WHERE especie_id = ?).
     *
     * @param especieId El ID de la Especie por la que se desea filtrar.
     * @return Una lista de entidades Genoma que pertenecen a la especie dada.
     */
    List<Genoma> findByEspecieId(Long especieId);

    // El método findByEspecieNombreCientifico se elimina porque no lo estamos usando
    // y el requisito del PDF especifica el filtro por ID.
}