package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.DTO.ChromosomeOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.entities.Chromosomes;
import com.BackendG1.GenomaBankAPI.repositories.ChromosomesRepository;
import com.BackendG1.GenomaBankAPI.services.IChromosomesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChromosomesServicesImpl implements IChromosomesService {
    private final ChromosomesRepository chromosomesRepository;


    public ChromosomesServicesImpl(ChromosomesRepository chromosomesRepository) {
        this.chromosomesRepository = chromosomesRepository;
    }


    @Override
    public List<ChromosomeOutDTO> listarCromosomas(Long genomeId) {
        List<Chromosomes> cromosomas;

        // Si no hay filtro → lista todos
        if (genomeId == null) {
            cromosomas = chromosomesRepository.findAll();
        } else {
            cromosomas = chromosomesRepository.findByGenomaId(genomeId);
        }

        // Convertir entidades a DTOs
        List<ChromosomeOutDTO> dtoLista = new ArrayList<>();
        for (Chromosomes c : cromosomas) {
            ChromosomeOutDTO dto = new ChromosomeOutDTO();
            dto.setId(c.getId());
            dto.setNombre(c.getNombre());
            dto.setLongitudPb(c.getLongitudPb());
            dto.setSecuenciaAdn(c.getSecuenciaAdn());
            dto.setGenomaId(c.getGenomaId());
            dtoLista.add(dto);
        }

        return dtoLista;
    }

    @Override
    public ChromosomeOutDTO crearCromosoma(GeneInDTO inDTO) {
        return null;
    }

    @Override
    public ChromosomeOutDTO consultarCromosoma(Long id) {
        return null;
    }

    @Override
    public String obtenerSecuencia(Long id) {
        return "";
    }

    @Override
    public ChromosomeOutDTO actualizarSecuencia(Long id, String nuevaSecuencia) {
        return null;
    }
}
