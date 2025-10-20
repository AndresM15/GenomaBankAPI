package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Chromosomes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChromosomesRepository extends JpaRepository<Chromosomes,Long> {
}
