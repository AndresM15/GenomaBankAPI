package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.ChromosomeOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;

import java.util.List;

public interface IChromosomesService {
    public ChromosomeOutDTO crearCromosoma(GeneInDTO inDTO);
    public ChromosomeOutDTO consultarCromosoma(Long id);
    List<ChromosomeOutDTO> listarCromosomas(Long genomeId);
    public String obtenerSecuencia(Long id);
    public ChromosomeOutDTO actualizarSecuencia(Long id, String nuevaSecuencia);
}
