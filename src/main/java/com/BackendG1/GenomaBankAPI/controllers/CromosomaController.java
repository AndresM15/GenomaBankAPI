package com.BackendG1.GenomaBankAPI.controllers;


import com.BackendG1.GenomaBankAPI.DTO.CromosomaDTO;
import com.BackendG1.GenomaBankAPI.services.ICromosomaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genoma/{genomaId}/cromosoma") // Ruta base anidada
public class CromosomaController {

    private final ICromosomaService cromosomaService;

    public CromosomaController(ICromosomaService cromosomaService) {
        this.cromosomaService = cromosomaService;
    }

    @GetMapping
    public ResponseEntity<List<CromosomaDTO>> obtenerCromosomasPorGenoma(@PathVariable String genomaId) {
        List<CromosomaDTO> cromosomas = cromosomaService.obtenerCromosomasPorGenoma(genomaId);
        return ResponseEntity.ok(cromosomas);
    }

    @GetMapping("/{chromosomeName}")
    public ResponseEntity<CromosomaDTO> consultarCromosomaEspecifico(
            @PathVariable String genomaId,
            @PathVariable String chromosomeName) {
        CromosomaDTO cromosoma = cromosomaService.obtenerPorId(genomaId, chromosomeName);
        if (cromosoma != null) {
            return ResponseEntity.ok(cromosoma);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CromosomaDTO> crearCromosoma(
            @PathVariable String genomeId,
            @RequestBody CromosomaDTO cromosomaDTO) {
        // Aseguramos que el DTO tenga el ID del genoma correcto de la URL
        cromosomaDTO.setIdGenoma(genomeId);
        CromosomaDTO nuevoCromosoma = cromosomaService.crearCromosoma(cromosomaDTO);
        return ResponseEntity.ok(nuevoCromosoma);
    }

    @PutMapping("/{chromosomeName}")
    public ResponseEntity<CromosomaDTO> actualizarCromosoma(
            @PathVariable String genomaId,
            @PathVariable String chromosomeName,
            @RequestBody CromosomaDTO cromosomaDTO) {
        CromosomaDTO actualizado = cromosomaService.actualizarCromosoma(genomaId, chromosomeName, cromosomaDTO);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // El endpoint de DELETE no estaba en tu lista, pero es parte del CRUD estándar.
    // Lo agrego por si lo necesitas. Si no, puedes eliminarlo.
    @DeleteMapping("/{chromosomeName}")
    public ResponseEntity<Void> eliminarCromosoma(
            @PathVariable String genomaId,
            @PathVariable String chromosomeName) {
        boolean eliminado = cromosomaService.eliminar(genomaId, chromosomeName);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}