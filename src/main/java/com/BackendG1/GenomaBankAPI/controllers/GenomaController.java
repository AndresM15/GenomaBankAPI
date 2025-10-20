package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.GenomaDTO;
import com.BackendG1.GenomaBankAPI.services.IGenomaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genomes")
public class GenomaController {

    private final IGenomaService genomaService;

    public GenomaController(IGenomaService genomaService) {
        this.genomaService = genomaService;
    }

    @GetMapping
    public ResponseEntity<List<GenomaDTO>> obtenerGenomas(@RequestParam(name = "speciesId", required = false) String speciesId) {
        List<GenomaDTO> genomas = genomaService.obtenerTodos(speciesId);
        return ResponseEntity.ok(genomas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenomaDTO> consultarGenomaEspecifico(@PathVariable String id) {
        GenomaDTO genoma = genomaService.obtenerPorId(id);
        if (genoma != null) {
            return ResponseEntity.ok(genoma);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GenomaDTO> crearGenoma(@RequestBody GenomaDTO genomaDTO) {
        GenomaDTO nuevoGenoma = genomaService.crearGenoma(genomaDTO);
        return ResponseEntity.ok(nuevoGenoma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenomaDTO> actualizarGenoma(@PathVariable String id, @RequestBody GenomaDTO genomaDTO) {
        GenomaDTO genomaActualizado = genomaService.actualizarGenoma(id, genomaDTO);
        if (genomaActualizado != null) {
            return ResponseEntity.ok(genomaActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenoma(@PathVariable String id) {
        boolean eliminado = genomaService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
