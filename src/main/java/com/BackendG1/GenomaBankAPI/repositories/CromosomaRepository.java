package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Cromosoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CromosomaRepository extends JpaRepository<Cromosoma, Long> {

    /**
     * Requisito: Listar cromosomas filtrados por genoma.
     * @param genomaId ID del genoma.
     * @return Lista de cromosomas de ese genoma.
     */
    List<Cromosoma> findByGenomaId(Long genomaId);

    /**
     * Necesario para el endpoint de Análisis (buscar genes en un rango de un cromosoma).
     * @param genomaId ID del genoma.
     * @param nombre Nombre del cromosoma.
     * @return El cromosoma.
     */
    Optional<Cromosoma> findByGenomaIdAndNombre(Long genomaId, String nombre);
}
