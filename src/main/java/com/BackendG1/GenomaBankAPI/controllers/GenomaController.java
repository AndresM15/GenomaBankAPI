package com.BackendG1.GenomaBankAPI.controllers;
import com.BackendG1.GenomaBankAPI.dto.GenomaDTO;
import com.BackendG1.GenomaBankAPI.services.IGenomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/genomes") // <-- Ruta corregida según el PDF
@RequiredArgsConstructor
public class GenomaController {
    private final IGenomaService genomaService;

    // GET /genomes?speciesId={id}
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<GenomaDTO> getAll(@RequestParam(name = "speciesId", required = false) Long speciesId) {
        return genomaService.findAll(speciesId);
    }

    // GET /genomes/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public GenomaDTO getById(@PathVariable Long id) {
        return genomaService.findById(id);
    }
}