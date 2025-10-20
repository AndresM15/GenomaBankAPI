package com.BackendG1.GenomaBankAPI.services.impl;
import com.BackendG1.GenomaBankAPI.dto.FuncionBiologicaDTO;
import com.BackendG1.GenomaBankAPI.entities.FuncionBiologica;
import com.BackendG1.GenomaBankAPI.repositories.FuncionBiologicaRepository;
import com.BackendG1.GenomaBankAPI.services.IFuncionBiologicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor
public class FuncionBiologicaServiceImpl implements IFuncionBiologicaService {
    private final FuncionBiologicaRepository repository;
    @Override public List<FuncionBiologicaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    @Override public FuncionBiologicaDTO findById(Long id) {
        FuncionBiologica fb = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(fb);
    }

    @Override
    public FuncionBiologicaDTO create(FuncionBiologicaDTO dto) {
        return null;
    }

    @Override
    public FuncionBiologicaDTO update(Long id, FuncionBiologicaDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private FuncionBiologicaDTO toDTO(FuncionBiologica entity) {
        FuncionBiologicaDTO dto = new FuncionBiologicaDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombreDescriptivo(entity.getNombreDescriptivo());
        if (entity.getCategoria() != null) {
            dto.setCategoria(entity.getCategoria().name());
        }
        return dto;
    }
}