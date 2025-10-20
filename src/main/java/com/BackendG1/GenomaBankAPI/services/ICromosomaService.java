package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.CromosomaDTO;

import java.util.List;

public interface ICromosomaService {
    List<CromosomaDTO> obtenerCromosomasPorGenoma(String genomeId);
    CromosomaDTO obtenerPorId(Long genomeId, String chromosomeName);
    CromosomaDTO crearCromosoma(CromosomaDTO cromosomaDTO);
    CromosomaDTO actualizarCromosoma(Long genomeId, String chromosomeName, CromosomaDTO cromosomaDTO);
    boolean eliminar(Long genomeId, String chromosomeName);
}
