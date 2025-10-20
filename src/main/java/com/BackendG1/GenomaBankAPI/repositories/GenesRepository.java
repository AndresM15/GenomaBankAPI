package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Genes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenesRepository extends JpaRepository<Genes, Long> {
    boolean existsBySymbol(String symbol);
}
