package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.GenomaDTO;

import java.util.List;

public interface IGenomaService {
    List<GenomaDTO> obtenerTodos(String speciesId);
    GenomaDTO obtenerPorId(Long id);
    GenomaDTO crearGenoma(GenomaDTO genomaDTO);
    GenomaDTO actualizarGenoma(Long id, GenomaDTO genomaDTO);
    boolean eliminar(Long id);
}
