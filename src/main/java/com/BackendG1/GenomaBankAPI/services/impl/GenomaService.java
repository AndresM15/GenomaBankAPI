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

                    // <-- CAMBIO: Tu DTO y Entidad usan "version"
                    genomaExistente.setVersion(genomaDTO.getVersion());

                    // <-- CAMBIO: Tu DTO usa "especieId" (un String) para la entrada.
                    // No puedes usar findById (que espera un Long).
                    // Debes usar el método que creamos en el paso 1.
                    Especie especie = especieRepository.findByNombreCientifico(genomaDTO.getEspecieId())
                            .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada con el nombre: " + genomaDTO.getEspecieId()));

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
        // Esto estaba bien, pero fallaba porque tu GenomaRepository usaba <Genoma, String>
        // Si ya lo corregiste a <Genoma, Long>, esto funcionará.
        if (genomaRepository.existsById(id)) {
            genomaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- MÉTODOS PRIVADOS DE CONVERSIÓN (MAPPERS) ---

    private GenomaDTO convertirA_DTO(Genoma genoma) {
        // <-- CAMBIO: Tu DTO tiene 4 campos.
        // Y los campos de Genoma son "id" y "version".
        return new GenomaDTO(
                genoma.getId(),
                genoma.getVersion(),
                genoma.getEspecie().getNombreCientifico(), // campo especieId del DTO
                genoma.getEspecie().getNombreCientifico()  // campo nombreCientificoEspecie del DTO
        );
    }

    private Genoma convertirA_Entidad(GenomaDTO genomaDTO) {
        // <-- CAMBIO: Usar el método "findByNombreCientifico" con el String "especieId" del DTO
        Especie especie = especieRepository.findByNombreCientifico(genomaDTO.getEspecieId())
                .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada con el nombre: " + genomaDTO.getEspecieId()));

        Genoma genoma = new Genoma();

        // <-- CAMBIO: Los campos son "id" y "version"
        genoma.setId(genomaDTO.getId());
        genoma.setVersion(genomaDTO.getVersion());
        genoma.setEspecie(especie);
        return genoma;
    }
}

