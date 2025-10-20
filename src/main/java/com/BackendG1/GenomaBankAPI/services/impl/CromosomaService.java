package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.DTO.CromosomaDTO;
import com.BackendG1.GenomaBankAPI.entities.Cromosoma;
import com.BackendG1.GenomaBankAPI.entities.CromosomaId;
import com.BackendG1.GenomaBankAPI.entities.Genoma;
import com.BackendG1.GenomaBankAPI.repositories.CromosomaRepository;
import com.BackendG1.GenomaBankAPI.repositories.GenomaRepository;
import com.BackendG1.GenomaBankAPI.services.ICromosomaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la lógica de negocio para Cromosomas.
 * Esta anotación @Service es crucial para que Spring la reconozca como un bean.
 */
@Service
public class CromosomaServiceImpl implements ICromosomaService {

    private final CromosomaRepository cromosomaRepository;
    private final GenomaRepository genomaRepository;

    public CromosomaServiceImpl(CromosomaRepository cromosomaRepository, GenomaRepository genomaRepository) {
        this.cromosomaRepository = cromosomaRepository;
        this.genomaRepository = genomaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CromosomaDTO> obtenerCromosomasPorGenoma(String genomeId) {
        // Usa el método del repositorio para encontrar todos los cromosomas de un genoma
        return cromosomaRepository.findByIdIdGenoma(genomeId).stream()
                .map(this::convertirA_DTO) // Convierte cada entidad a DTO
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CromosomaDTO obtenerPorId(String genomeId, String chromosomeName) {
        // Crea el objeto de la clave compuesta para la búsqueda
        CromosomaId id = new CromosomaId(genomeId, chromosomeName);
        return cromosomaRepository.findById(id)
                .map(this::convertirA_DTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public CromosomaDTO crearCromosoma(CromosomaDTO cromosomaDTO) {
        // Convierte el DTO a una entidad
        Cromosoma cromosoma = convertirA_Entidad(cromosomaDTO);
        // Guarda la entidad en la base de datos
        Cromosoma nuevoCromosoma = cromosomaRepository.save(cromosoma);
        // Devuelve el resultado como un DTO
        return convertirA_DTO(nuevoCromosoma);
    }

    @Override
    @Transactional
    public CromosomaDTO actualizarCromosoma(String genomeId, String chromosomeName, CromosomaDTO cromosomaDTO) {
        CromosomaId id = new CromosomaId(genomeId, chromosomeName);
        return cromosomaRepository.findById(id)
                .map(cromosomaExistente -> {
                    // Actualiza solo los campos permitidos
                    cromosomaExistente.setLongitudParesBase(cromosomaDTO.getLongitudParesBase());
                    cromosomaExistente.setSecuenciaADN(cromosomaDTO.getSecuenciaADN());

                    Cromosoma actualizado = cromosomaRepository.save(cromosomaExistente);
                    return convertirA_DTO(actualizado);
                })
                .orElse(null); // Devuelve null si no se encuentra
    }

    @Override
    @Transactional
    public boolean eliminar(String genomeId, String chromosomeName) {
        CromosomaId id = new CromosomaId(genomeId, chromosomeName);
        if (cromosomaRepository.existsById(id)) {
            cromosomaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- Métodos de Mapeo ---

    private CromosomaDTO convertirA_DTO(Cromosoma cromosoma) {
        return new CromosomaDTO(
                cromosoma.getId().getNombreCromosoma(),
                cromosoma.getLongitudParesBase(),
                // Por optimización, no siempre se devuelve la secuencia completa aquí
                cromosoma.getSecuenciaADN(),
                cromosoma.getId().getIdGenoma()
        );
    }

    private Cromosoma convertirA_Entidad(CromosomaDTO dto) {
        // Es crucial verificar que el genoma padre exista
        Genoma genoma = genomaRepository.findById(dto.getIdGenoma())
                .orElseThrow(() -> new EntityNotFoundException("Genoma no encontrado con ID: " + dto.getIdGenoma()));

        Cromosoma cromosoma = new Cromosoma();
        cromosoma.setId(new CromosomaId(dto.getIdGenoma(), dto.getNombreCromosoma()));
        cromosoma.setLongitudParesBase(dto.getLongitudParesBase());
        cromosoma.setSecuenciaADN(dto.getSecuenciaADN());
        cromosoma.setGenoma(genoma);
        return cromosoma;
    }
}
