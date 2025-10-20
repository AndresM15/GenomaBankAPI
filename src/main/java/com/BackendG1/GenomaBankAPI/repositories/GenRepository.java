package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Gen; // <<< SOLUCIÓN: Entidad correcta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// <<< SOLUCIÓN: Debe ser JpaRepository<Gen, Long>
public interface GenRepository extends JpaRepository<Gen, Long> {

    List<Gen> findByCromosomaId(Long cromosomaId);

    List<Gen> findBySimbolo(String simbolo);

    // Esta es la consulta correcta para el análisis por rango
    List<Gen> findByCromosomaIdAndPosicionFinalGreaterThanEqualAndPosicionInicioLessThanEqual(
            Long cromosomaId, Integer start, Integer end
    );
}