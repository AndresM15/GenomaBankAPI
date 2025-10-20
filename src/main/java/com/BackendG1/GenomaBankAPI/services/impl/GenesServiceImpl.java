package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.UpdateGeneDTO;
import com.BackendG1.GenomaBankAPI.services.GenesService;

import java.util.List;

public class GenesServiceImpl implements GenesService {

    @Override
    public GeneOutDTO crearGen(GeneInDTO inDTO) {
        return null;
    }

    @Override
    public GeneOutDTO consultarGen(Long id) {
        return null;
    }

    @Override
    public List<GeneOutDTO> ListarGenes(String ChromosomeId, Integer startPos, Integer endPos, String symbol) {
        return List.of();
    }

    @Override
    public GeneOutDTO actualizarGen(Long id, UpdateGeneDTO inDTO) {
        return null;
    }

    @Override
    public void eliminarGen(Long id) {

    }
}
