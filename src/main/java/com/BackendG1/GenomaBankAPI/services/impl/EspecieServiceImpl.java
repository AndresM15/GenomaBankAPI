package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.dto.EspecieOutDTO;
import com.BackendG1.GenomaBankAPI.entities.Especie;
import com.BackendG1.GenomaBankAPI.repositories.EspecieRepository;
import com.BackendG1.GenomaBankAPI.services.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import com.BackendG1.GenomaBankAPI.dto.EspecieInDTO;
import com.BackendG1.GenomaBankAPI.dto.EspecieOutDTO;
import com.BackendG1.GenomaBankAPI.entities.Especie;
import com.BackendG1.GenomaBankAPI.repositories.EspecieRepository;
import com.BackendG1.GenomaBankAPI.services.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository especieRepository;

    @Override
    public List<EspecieOutDTO> listSpecies() {
        return especieRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }



    @Override
    public EspecieOutDTO findSpeciesById(Long id) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie no encontrada con el ID: " + id));
        return mapToDTO(especie);
    }

    @Override
    public EspecieOutDTO createSpecies(EspecieInDTO dto) {
        // Mapeamos el DTO de entrada a una entidad
        Especie especie = new Especie();
        especie.setNombreCientifico(dto.getNombreCientifico());
        especie.setNombreComun(dto.getNombreComun());

        // Guardamos la nueva entidad en la base de datos
        Especie nuevaEspecie = especieRepository.save(especie);

        // Mapeamos la entidad guardada (con su nuevo ID) a un DTO de salida
        return mapToDTO(nuevaEspecie);
    }

    @Override
    public EspecieOutDTO updateSpecies(Long id, EspecieInDTO dto) {
        // 1. Buscamos la especie existente
        Especie especieExistente = findEspecieByIdOrThrow(id);

        // 2. Actualizamos sus datos con los del DTO
        especieExistente.setNombreCientifico(dto.getNombreCientifico());
        especieExistente.setNombreComun(dto.getNombreComun());

        // 3. Guardamos la entidad actualizada
        Especie especieActualizada = especieRepository.save(especieExistente);

        // 4. Devolvemos el DTO con los datos actualizados
        return mapToDTO(especieActualizada);
    }

    private EspecieOutDTO mapToDTO(Especie especie) {
        EspecieOutDTO dto = new EspecieOutDTO();
        dto.setId(especie.getId());
        dto.setNombreCientifico(especie.getNombreCientifico());
        dto.setNombreComun(especie.getNombreComun());
        return dto;
    }

    private Especie findEspecieByIdOrThrow(Long id) {
        return especieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especie no encontrada con el ID: " + id));
    }

    @Override
    public void deleteSpecies(Long id) {
        // 1. Verificamos que la especie exista antes de intentar borrarla.
        // Si no existe, el método findEspecieByIdOrThrow lanzará un 404.
        findEspecieByIdOrThrow(id);

        // 2. Si existe, la eliminamos.
        especieRepository.deleteById(id);
    }
}