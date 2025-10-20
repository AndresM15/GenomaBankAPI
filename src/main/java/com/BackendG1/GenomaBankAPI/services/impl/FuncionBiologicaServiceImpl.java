package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.DTO.FuncionBiologicaDTO;
import com.BackendG1.GenomaBankAPI.entities.FuncionBiologica;
import com.BackendG1.GenomaBankAPI.repositories.FuncionBiologicaRepository;
import com.BackendG1.GenomaBankAPI.services.IFuncionBiologicaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionBiologicaServiceImpl implements IFuncionBiologicaService {

    private final FuncionBiologicaRepository repository;

    public FuncionBiologicaServiceImpl(FuncionBiologicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FuncionBiologicaDTO> listarFunciones() {
        return repository.findAll().stream().map(this::convertirA_DTO).collect(Collectors.toList());
    }

    @Override
    public FuncionBiologicaDTO buscarPorId(String id) {
        return repository.findById(id).map(this::convertirA_DTO).orElse(null);
    }

    @Override
    public FuncionBiologicaDTO crearFuncion(FuncionBiologicaDTO dto) {
        FuncionBiologica funcion = convertirA_Entidad(dto);
        return convertirA_DTO(repository.save(funcion));
    }

    @Override
    public FuncionBiologicaDTO actualizarFuncion(String id, FuncionBiologicaDTO dto) {
        FuncionBiologica funcion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Función no encontrada con ID: " + id));

        funcion.setNombreDescriptivo(dto.getNombreDescriptivo());
        funcion.setCategoria(FuncionBiologica.CategoriaFuncion.valueOf(dto.getCategoria()));
        return convertirA_DTO(repository.save(funcion));
    }

    @Override
    public void eliminarFuncion(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Función no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    // --- MAPPERS ---
    private FuncionBiologicaDTO convertirA_DTO(FuncionBiologica funcion) {
        return new FuncionBiologicaDTO(funcion.getCodigoFuncion(), funcion.getNombreDescriptivo(), funcion.getCategoria().name());
    }

    private FuncionBiologica convertirA_Entidad(FuncionBiologicaDTO dto) {
        FuncionBiologica funcion = new FuncionBiologica();
        funcion.setCodigoFuncion(dto.getCodigoFuncion());
        funcion.setNombreDescriptivo(dto.getNombreDescriptivo());
        funcion.setCategoria(FuncionBiologica.CategoriaFuncion.valueOf(dto.getCategoria()));
        return funcion;
    }
}
