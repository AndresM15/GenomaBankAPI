package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.ChromosomesOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;

import java.util.List;

public interface IChromosomesService {
    public ChromosomesOutDTO crearCromosoma(GeneInDTO inDTO);
    public ChromosomesOutDTO consultarCromosoma(Long id);
    public List<ChromosomesOutDTO> listarCromosomas(Long ChromosomeId, Integer startPos, Integer endPos, String symbol);
    public String obtenerSecuencia(Long id);
    public ChromosomesOutDTO actualizarSecuencia(Long id, String nuevaSecuencia);
}
