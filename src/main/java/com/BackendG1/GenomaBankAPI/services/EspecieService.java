package com.BackendG1.GenomaBankAPI.services;


import com.BackendG1.GenomaBankAPI.dto.EspecieInDTO;
import com.BackendG1.GenomaBankAPI.dto.EspecieOutDTO;
import java.util.List;

public interface EspecieService {
    List<EspecieOutDTO> listSpecies();
    EspecieOutDTO findSpeciesById(Long id);
    EspecieOutDTO createSpecies(EspecieInDTO dto);
    EspecieOutDTO updateSpecies(Long id, EspecieInDTO dto);
    void deleteSpecies(Long id);
}