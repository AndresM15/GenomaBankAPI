package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.dto.CromosomaDTO;
import com.BackendG1.GenomaBankAPI.services.ICromosomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/chromosomes") // <-- Ruta corregida según el PDF
@RequiredArgsConstructor
public class CromosomaController {
    private final ICromosomaService cromosomaService;

    // GET /chromosomes/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CromosomaDTO getById(@PathVariable Long id) {
        return cromosomaService.findById(id);
    }

    // GET /chromosomes?genomeId={id}
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CromosomaDTO> getByGenomaId(@RequestParam(required = false) Long genomeId) {
        // Si no se provee genomeId, se podrían listar todos, pero es mejor ser explícito.
        if (genomeId != null) {
            return cromosomaService.findAllByGenomaId(genomeId);
        }
        // Opcional: devolver una lista vacía o un error si no se filtra
        return List.of();
    }
}