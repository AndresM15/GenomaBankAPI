package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.CromosomaDTO;

import java.util.List;

public interface ICromosomaService {
    List<CromosomaDTO> obtenerCromosomasPorGenoma(String genomeId);
    CromosomaDTO obtenerPorId(String genomeId, String chromosomeName);
    CromosomaDTO crearCromosoma(CromosomaDTO cromosomaDTO);
    CromosomaDTO actualizarCromosoma(String genomeId, String chromosomeName, CromosomaDTO cromosomaDTO);
    boolean eliminar(String genomeId, String chromosomeName);
}
