package com.BackendG1.GenomaBankAPI.services.impl;
import com.BackendG1.GenomaBankAPI.dto.CromosomaDTO;import com.BackendG1.GenomaBankAPI.dto.CromosomaDTO;
import com.BackendG1.GenomaBankAPI.entities.Cromosoma;
import com.BackendG1.GenomaBankAPI.repositories.CromosomaRepository;
import com.BackendG1.GenomaBankAPI.services.ICromosomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor
public class CromosomaServiceImpl implements ICromosomaService {
    private final CromosomaRepository cromosomaRepository;
    @Override public List<CromosomaDTO> findAllByGenomaId(Long genomaId) {
        return cromosomaRepository.findByGenomaId(genomaId).stream().map(this::toDTO).collect(Collectors.toList());
    }
    @Override public CromosomaDTO findById(Long id) {
        Cromosoma c = cromosomaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(c);
    }
    private CromosomaDTO toDTO(Cromosoma entity) {
        CromosomaDTO dto = new CromosomaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setLongitudPb(entity.getLongitudPb());
        if (entity.getGenoma() != null) {
            dto.setGenomaId(entity.getGenoma().getId());
        }
        return dto;
    }
}