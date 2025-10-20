package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.DTO.GenomaDTO;
import com.BackendG1.GenomaBankAPI.entities.Genoma;
import com.BackendG1.GenomaBankAPI.repositories.GenomaRepository;
import com.BackendG1.GenomaBankAPI.services.IGenomaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.BackendG1.GenomaBankAPI.repositories.EspecieRepository;
import com.BackendG1.GenomaBankAPI.entities.Especie;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la lógica de negocio para la gestión de Genomas.
 */
@Service
public class GenomaServiceImpl implements IGenomaService {

    private final GenomaRepository genomaRepository;
    private final EspecieRepository especieRepository; // Necesario para manejar la FK

    public GenomaServiceImpl(GenomaRepository genomaRepository, EspecieRepository especieRepository) {
        this.genomaRepository = genomaRepository;
        this.especieRepository = especieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenomaDTO> obtenerTodos(String speciesId) {
        List<Genoma> genomas;
        if (speciesId != null && !speciesId.isEmpty()) {
            // Filtrar por el nombre científico de la especie
            genomas = genomaRepository.findByEspecieNombreCientifico(speciesId);
        } else {
            // Obtener todos los genomas si no hay filtro
            genomas = genomaRepository.findAll();
        }
        // Convertir la lista de entidades a una lista de DTOs
        return genomas.stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GenomaDTO obtenerPorId(Long id) {
        return genomaRepository.findById(id)
                .map(this::convertirA_DTO) // Si se encuentra, se convierte a DTO
                .orElse(null); // Si no, se devuelve null
    }

    @Override
    @Transactional
    public GenomaDTO crearGenoma(GenomaDTO genomaDTO) {
        // Convertir el DTO a una entidad Genoma
        Genoma genoma = convertirA_Entidad(genomaDTO);
        // Guardar la nueva entidad en la base de datos
        Genoma nuevoGenoma = genomaRepository.save(genoma);
        // Devolver el resultado como un DTO
        return convertirA_DTO(nuevoGenoma);
    }

    @Override
    @Transactional
    public GenomaDTO actualizarGenoma(Long id, GenomaDTO genomaDTO) {
        // Buscar el genoma existente por su ID
        return genomaRepository.findById(id)
                .map(genomaExistente -> {
                    // Actualizar los campos del genoma existente
                    genomaExistente.setVersionEnsamblaje(genomaDTO.getVersionEnsamblaje());

                    // Buscar la nueva especie y actualizar la relación
                    Especie especie = especieRepository.findById(genomaDTO.getNombreCientificoEspecie())
                            .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada con el ID: " + genomaDTO.getNombreCientificoEspecie()));
                    genomaExistente.setEspecie(especie);

                    // Guardar los cambios
                    Genoma genomaActualizado = genomaRepository.save(genomaExistente);
                    return convertirA_DTO(genomaActualizado);
                })
                .orElse(null); // Devolver null si no se encuentra el genoma
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (genomaRepository.existsById(id)) {
            genomaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- MÉTODOS PRIVADOS DE CONVERSIÓN (MAPPERS) ---

    /**
     * Convierte una entidad Genoma a su correspondiente GenomaDTO.
     */
    private GenomaDTO convertirA_DTO(Genoma genoma) {
        return new GenomaDTO(
                genoma.getIdGenoma(),
                genoma.getVersionEnsamblaje(),
                genoma.getEspecie().getNombreCientifico() // Obtener el ID de la especie de la entidad anidada
        );
    }

    /**
     * Convierte un GenomaDTO a una entidad Genoma.
     */
    private Genoma convertirA_Entidad(GenomaDTO genomaDTO) {
        // Buscar la entidad Especie a la que pertenece este genoma. Si no existe, lanza una excepción.
        Especie especie = especieRepository.findById(genomaDTO.getNombreCientificoEspecie())
                .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada con el ID: " + genomaDTO.getNombreCientificoEspecie()));

        Genoma genoma = new Genoma();
        genoma.setIdGenoma(genomaDTO.getIdGenoma());
        genoma.setVersionEnsamblaje(genomaDTO.getVersionEnsamblaje());
        genoma.setEspecie(especie); // Asignar la entidad Especie completa a la relación
        return genoma;
    }
}

