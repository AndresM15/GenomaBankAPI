package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.dto.GenomaDTO;
import com.BackendG1.GenomaBankAPI.entities.Genoma;
import com.BackendG1.GenomaBankAPI.repositories.GenomaRepository;
import com.BackendG1.GenomaBankAPI.services.IGenomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor
public class GenomaServiceImpl implements IGenomaService {
    private final GenomaRepository genomaRepository;
    @Override public List<GenomaDTO> findAll(Long especieId) {
        List<Genoma> genomas;
        if (especieId != null) {
            genomas = genomaRepository.findByEspecieId(especieId);
        } else {
            genomas = genomaRepository.findAll();
        }
        return genomas.stream().map(this::toDTO).collect(Collectors.toList());
    }
    @Override public GenomaDTO findById(Long id) {
        Genoma genoma = genomaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(genoma);
    }
    private GenomaDTO toDTO(Genoma entity) {
        GenomaDTO dto = new GenomaDTO();
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        if (entity.getEspecie() != null) {
            dto.setEspecieId(entity.getEspecie().getId());
        }
        return dto;
    }
}