package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.UpdateGeneDTO;
import com.BackendG1.GenomaBankAPI.entities.Genes;

import java.util.List;

// Interfaz de servicio que define el contrato de negocio para la gestión de Genes.

//* @author Juan Camilo (Refactorización y Lógica de Negocio)
//* @author Tomás (Funcionalidad de Actualización y Eliminación)

public interface GenesService {

    // -- ENDPOINTS DE JUANCA  --

    public GeneOutDTO crearGen(GeneInDTO inDTO);
    public GeneOutDTO consultarGen(Long id);
    public List<GeneOutDTO> ListarGenes(String ChromosomeId, Integer startPos,Integer endPos,String symbol);

    // -- ENDPOINTS DE TOMAS --

    public GeneOutDTO actualizarGen(Long id, UpdateGeneDTO inDTO);
    public void eliminarGen(Long id);
}
