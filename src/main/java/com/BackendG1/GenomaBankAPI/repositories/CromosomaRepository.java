package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Cromosoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
// <<< SOLUCIÓN: El ID es de tipo Long
public interface CromosomaRepository extends JpaRepository<Cromosoma, Long> {

    // Método para listar cromosomas por el ID de su genoma
    List<Cromosoma> findByGenomaId(Long genomaId);

    // Método opcional para buscar por nombre dentro de un genoma
    Optional<Cromosoma> findByGenomaIdAndNombre(Long genomaId, String nombre);
}