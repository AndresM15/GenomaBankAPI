package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Genoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenomaRepository extends JpaRepository<Genoma, Long> {
    List<Genoma> findByEspecieNombreCientifico(String nombreCientifico);
}
