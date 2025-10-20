package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.entities.Genes;
import com.BackendG1.GenomaBankAPI.repositories.GenesRepository;
import com.BackendG1.GenomaBankAPI.services.GenesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenesServiceImpl implements GenesService {

    private final GenesRepository genesRepository;

    public GenesServiceImpl(GenesRepository genesRepository) {
        this.genesRepository = genesRepository;
    }

    @Override
    public List<Genes> findAll() {
        return genesRepository.findAll();
    }

    @Override
    public Genes findById(Long id) {
        return genesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gen no encontrado con id: " + id));
    }

    @Override
    public Genes updateGene(Long id, Genes gene) {
        Genes existing = findById(id);
        existing.setSymbol(gene.getSymbol());
        existing.setStartPos(gene.getStartPos());
        existing.setEndPos(gene.getEndPos());
        existing.setStrand(gene.getStrand());
        existing.setSequence(gene.getSequence());
        return genesRepository.save(existing);
    }

    @Override
    public void deleteGene(Long id) {
        genesRepository.deleteById(id);
    }
}

