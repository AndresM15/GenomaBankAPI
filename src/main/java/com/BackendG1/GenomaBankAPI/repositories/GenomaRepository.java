package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Genoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenomaRepository extends JpaRepository<Genoma, Long> {

    /**
     * Requisito: Listar genomas filtrados por especie.
     * @param especieId ID de la especie.
     * @return Lista de genomas de esa especie.
     */
    List<Genoma> findByEspecieId(Long especieId);
}
