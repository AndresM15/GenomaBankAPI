package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.UpdateGeneDTO;
import com.BackendG1.GenomaBankAPI.entities.Chromosomes;
import com.BackendG1.GenomaBankAPI.entities.Genes;
import com.BackendG1.GenomaBankAPI.exceptions.DuplicateResourceException;
import com.BackendG1.GenomaBankAPI.exceptions.NotFoundException;
import com.BackendG1.GenomaBankAPI.repositories.ChromosomesRepository;
import com.BackendG1.GenomaBankAPI.repositories.GenesRepository;
import com.BackendG1.GenomaBankAPI.services.GenesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenesServiceImpl implements GenesService {
    private final GenesRepository genesRepository;
    private final ChromosomesRepository chromosomesRepository;

    public GenesServiceImpl(GenesRepository genesRepository, ChromosomesRepository chromosomesRepository) {
        this.genesRepository = genesRepository;
        this.chromosomesRepository = chromosomesRepository;
    }

    // Metodo POST
    @Override
    public GeneOutDTO crearGen(GeneInDTO inDTO) {
        if (genesRepository.existsBySymbol(inDTO.getSymbol()))
            throw new DuplicateResourceException("El símbolo del gen '" + inDTO.getSymbol() + "' ya existe.");

        Genes entidad = new Genes();
        Chromosomes cromosoma = chromosomesRepository.findById(inDTO.getCromosomaId()).get();

        entidad.setSymbol(inDTO.getSymbol());
        entidad.setStartPos(inDTO.getStartPos());
        entidad.setEndPos(inDTO.getEndPos());
        entidad.setStrand(inDTO.getStrand());
        entidad.setSequence(inDTO.getSequence());
        entidad.setCromosoma(cromosoma);

        Genes entidadGuardada = this.genesRepository.save(entidad);
        GeneOutDTO dto = new GeneOutDTO();

        dto.setId(entidadGuardada.getId());
        dto.setSymbol(entidadGuardada.getSymbol());
        dto.setStartPos(entidadGuardada.getStartPos());
        dto.setEndPos(entidadGuardada.getEndPos());
        dto.setStrand(entidadGuardada.getStrand());
        dto.setSequence(entidadGuardada.getSequence());
        dto.setCromosomaId(entidadGuardada.getCromosoma().getId());
        return dto;
    }

    @Override
    public GeneOutDTO consultarGen(Long id) {
        Optional<Genes> genes = this.genesRepository.findById(id);
        if(genes.isEmpty()){
            throw new NotFoundException("El gen consultado: " + id + " no existe");
        }

        Genes entidad = genes.get();
        GeneOutDTO dto = new GeneOutDTO();

        dto.setId(entidad.getId());
        dto.setSymbol(entidad.getSymbol());
        dto.setStartPos(entidad.getStartPos());
        dto.setEndPos(entidad.getEndPos());
        dto.setStrand(entidad.getStrand());
        dto.setSequence(entidad.getSequence());
        dto.setCromosomaId(entidad.getCromosoma().getId());
        return dto;
    }

    @Override
    public List<GeneOutDTO> listarGenes(Long chromosomeId, Integer startPos, Integer endPos, String symbol) {
        List<Genes> genes = new ArrayList<>();

        // Si no hay filtros, devuelve todo
        if (chromosomeId == null && startPos == null && endPos == null && symbol == null) {
            genes = genesRepository.findAll();
        }
        // Filtro por cromosoma
        else if (chromosomeId != null) {
            genes = genesRepository.findByCromosoma_Id(chromosomeId);
        }
        // Filtro por símbolo
        else if (symbol != null) {
            genes = genesRepository.findBySymbolContainingIgnoreCase(symbol);
        }

        // Convertir a DTO
        List<GeneOutDTO> dtoLista = new ArrayList<>();
        for (Genes gen : genes) {
            GeneOutDTO dto = new GeneOutDTO();
            dto.setId(gen.getId());
            dto.setSymbol(gen.getSymbol());
            dto.setStartPos(gen.getStartPos());
            dto.setEndPos(gen.getEndPos());
            dto.setStrand(gen.getStrand());
            dto.setSequence(gen.getSequence());
            dto.setCromosomaId(gen.getCromosoma().getId());
            dtoLista.add(dto);
        }
        return dtoLista;
    }

    @Override
    public GeneOutDTO actualizarGen(Long id, UpdateGeneDTO inDTO) {
        return null;
    }

    @Override
    public void eliminarGen(Long id) {

    }
}
