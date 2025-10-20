package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {

    // Spring Data JPA automáticamente permite buscar por nombre cientifico si es necesario:
    // Optional<Especie> findByNombreCientifico(String nombreCientifico);
}
