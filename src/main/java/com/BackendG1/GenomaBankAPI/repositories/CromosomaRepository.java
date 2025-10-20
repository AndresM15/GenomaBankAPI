package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Cromosoma;
import com.BackendG1.GenomaBankAPI.entities.CromosomaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CromosomaRepository extends JpaRepository<Cromosoma, CromosomaId> {

    // Método para encontrar todos los cromosomas de un genoma específico
    // Spring Data JPA entiende "findByIdIdGenoma" como "buscar por el campo 'id' que contiene un campo 'idGenoma'"
    List<Cromosoma> findByIdIdGenoma(String idGenoma);
}
