package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Chromosomes;
import com.BackendG1.GenomaBankAPI.entities.Genes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChromosomesRepository extends JpaRepository<Chromosomes,Long> {
    List<Chromosomes> findByGenomaId(Long genomaId);


}
