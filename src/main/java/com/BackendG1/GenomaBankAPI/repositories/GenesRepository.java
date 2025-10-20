package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Genes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenesRepository extends JpaRepository<Genes, Long> {
    boolean existsBySymbol(String symbol);

    //Metodos para filtrar
    List<Genes> findByCromosoma_Id(Long cromosomaId);
    List<Genes> findBySymbolContainingIgnoreCase(String symbol);
}
