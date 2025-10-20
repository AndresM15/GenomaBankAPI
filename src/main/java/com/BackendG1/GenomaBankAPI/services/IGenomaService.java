package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.DTO.GenomaDTO;

import java.util.List;

public interface IGenomaService {
    List<GenomaDTO> obtenerTodos(String speciesId);
    GenomaDTO obtenerPorId(String id);
    GenomaDTO crearGenoma(GenomaDTO genomaDTO);
    GenomaDTO actualizarGenoma(String id, GenomaDTO genomaDTO);
    boolean eliminar(String id);
}
