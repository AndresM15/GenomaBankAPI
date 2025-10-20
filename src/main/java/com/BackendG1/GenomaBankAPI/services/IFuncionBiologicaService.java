package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.dto.FuncionBiologicaDTO;
import java.util.List;
public interface IFuncionBiologicaService {
    List<FuncionBiologicaDTO> findAll();
    FuncionBiologicaDTO findById(Long id);
    FuncionBiologicaDTO create(FuncionBiologicaDTO dto);
    FuncionBiologicaDTO update(Long id,FuncionBiologicaDTO dto);
    void delete(Long id);
    // Agregaremos los demás métodos cuando los necesitemos
}