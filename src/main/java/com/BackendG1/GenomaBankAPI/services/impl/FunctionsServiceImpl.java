package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.entities.Functions;
import com.BackendG1.GenomaBankAPI.repositories.FunctionsRepository;
import com.BackendG1.GenomaBankAPI.services.FunctionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionsServiceImpl implements FunctionsService {

    private final FunctionsRepository functionsRepository;

    public FunctionsServiceImpl(FunctionsRepository functionsRepository) {
        this.functionsRepository = functionsRepository;
    }

    @Override
    public List<Functions> findAllFiltered(String code, String category) {
        if (code != null && category != null)
            return functionsRepository.findByCodeAndCategory(code, category);
        else if (code != null)
            return functionsRepository.findByCode(code);
        else if (category != null)
            return functionsRepository.findByCategory(category);
        else
            return functionsRepository.findAll();
    }

    @Override
    public Functions findById(Long id) {
        return functionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Función no encontrada con id: " + id));
    }
}