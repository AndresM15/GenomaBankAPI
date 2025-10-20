package com.BackendG1.GenomaBankAPI.services;
import com.BackendG1.GenomaBankAPI.dto.CromosomaDTO;
import java.util.List;
public interface ICromosomaService {
    List<CromosomaDTO> findAllByGenomaId(Long genomaId);
    CromosomaDTO findById(Long id);
    // Agregaremos los demás métodos cuando los necesitemos
}