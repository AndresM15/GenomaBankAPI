package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.entities.Genes;
import java.util.List;

public interface GenesService {
    List<Genes> findAll();
    Genes findById(Long id);
    Genes updateGene(Long id, Genes gene);
    void deleteGene(Long id);
}

