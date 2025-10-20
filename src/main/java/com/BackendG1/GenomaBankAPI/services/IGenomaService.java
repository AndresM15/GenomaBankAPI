package com.BackendG1.GenomaBankAPI.services;
import com.BackendG1.GenomaBankAPI.dto.GenomaDTO;
import java.util.List;
public interface IGenomaService {
    List<GenomaDTO> findAll(Long especieId);
    GenomaDTO findById(Long id);
    // Agregaremos los demás métodos cuando los necesitemos
}