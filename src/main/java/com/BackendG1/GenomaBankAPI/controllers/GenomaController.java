package com.BackendG1.GenomaBankAPI.controllers;

// Corregido: el paquete DTO suele ser en minúsculas -> dtos
import com.BackendG1.GenomaBankAPI.DTO.GenomaDTO;
import com.BackendG1.GenomaBankAPI.services.IGenomaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.BackendG1.GenomaBankAPI.entities.Genoma;

import java.util.List;

@RestController
@RequestMapping("/genomes") // Ruta oficial del PDF
public class GenomaController {

    private final IGenomaService genomaService;

    public GenomaController(IGenomaService genomaService) {
        this.genomaService = genomaService;
    }

    /**
     * Endpoint para listar todos los genomas o filtrarlos por el ID de la especie.
     * Corresponde a: GET /genomes o GET /genomes?speciesId={id}
     */
    @GetMapping
    public ResponseEntity<List<GenomaDTO>> obtenerGenomas(@RequestParam(name = "speciesId", required = false) String speciesId) {
        List<GenomaDTO> genomas = genomaService.obtenerTodos(speciesId);
        return ResponseEntity.ok(genomas);
    }

    /**
     * Endpoint para consultar un genoma específico por su ID.
     * Corresponde a: GET /genomes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenomaDTO> consultarGenomaEspecifico(@PathVariable Long id) {
        GenomaDTO genoma = genomaService.obtenerPorId(id);
        if (genoma != null) {
            return ResponseEntity.ok(genoma);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para crear un nuevo genoma. (Solo ADMIN)
     * Corresponde a: POST /genomes
     */
    @PostMapping
    public ResponseEntity<GenomaDTO> crearGenoma(@RequestBody GenomaDTO genomaDTO) {
        GenomaDTO nuevoGenoma = genomaService.crearGenoma(genomaDTO);
        return ResponseEntity.ok(nuevoGenoma);
    }

    /**
     * Endpoint para actualizar un genoma existente. (Solo ADMIN)
     * Corresponde a: PUT /genomes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<GenomaDTO> actualizarGenoma(@PathVariable Long id, @RequestBody GenomaDTO genomaDTO) {
        GenomaDTO genomaActualizado = genomaService.actualizarGenoma(id, genomaDTO);
        if (genomaActualizado != null) {
            return ResponseEntity.ok(genomaActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para eliminar un genoma. (Solo ADMIN)
     * Corresponde a: DELETE /genomes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenoma(@PathVariable Long id) {
        boolean eliminado = genomaService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

