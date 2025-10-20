package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.FuncionBiologicaDTO;
import java.util.List;

public interface IFuncionBiologicaService {
    List<FuncionBiologicaDTO> listarFunciones();
    FuncionBiologicaDTO buscarPorId(String id);
    FuncionBiologicaDTO crearFuncion(FuncionBiologicaDTO dto);
    FuncionBiologicaDTO actualizarFuncion(String id, FuncionBiologicaDTO dto);
    void eliminarFuncion(String id);
}
