package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Gen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenRepository extends JpaRepository<Gen, Long> {

    /**
     * Requisito: Listar genes filtrados por cromosoma.
     * @param cromosomaId ID del cromosoma.
     * @return Lista de genes en ese cromosoma.
     */
    List<Gen> findByCromosomaId(Long cromosomaId);

    /**
     * Requisito: Filtrar genes por símbolo (para búsqueda).
     * @param simbolo Símbolo del gen.
     * @return Lista de genes con ese símbolo.
     */
    List<Gen> findBySimbolo(String simbolo);

    /**
     * Consulta para el endpoint de Análisis:
     * GET /analysis/genes?chromosomeld=&start=&end=
     * Busca todos los genes que se superpongan o estén contenidos dentro del rango [start, end].
     * Se usa una consulta HQL/JPQL para lógica compleja.
     * * @param cromosomaId ID del cromosoma donde buscar.
     * @param start Posición inicial del rango de búsqueda.
     * @param end Posición final del rango de búsqueda.
     * @return Lista de genes que se superponen con el rango.
     */
    List<Gen> findByCromosomaIdAndPosicionFinalGreaterThanEqualAndPosicionInicioLessThanEqual(
            Long cromosomaId, Integer start, Integer end
    );
}