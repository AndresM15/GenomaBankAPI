package com.BackendG1.GenomaBankAPI.controllers;

// Se necesitará un IGenService y su implementación para la lógica completa.
// Este controlador es un ejemplo de cómo se estructurarían los endpoints.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class GenController {

    // Inyectar el servicio de Gen aquí...
    // private final IGenService genService;

    // GET /genes/{id}/functions → Listar las funciones asociadas a un gen
    @GetMapping("/genes/{genomeId}/{chromosomeName}/{geneSymbol}/functions")
    public ResponseEntity<List<?>> listarFuncionesDeGen(
            @PathVariable String genomeId,
            @PathVariable String chromosomeName,
            @PathVariable String geneSymbol) {
        // Lógica para llamar al servicio y listar funciones del gen
        return ResponseEntity.ok().build(); // Placeholder
    }

    // POST /genes/{id}/functions/{functionId} → Asociar una función a un gen
    @PostMapping("/genes/{genomeId}/{chromosomeName}/{geneSymbol}/functions/{functionId}")
    public ResponseEntity<Void> asociarFuncionAGen(
            @PathVariable String genomeId,
            @PathVariable String chromosomeName,
            @PathVariable String geneSymbol,
            @PathVariable String functionId) {
        // Lógica para llamar al servicio y crear la asociación
        return ResponseEntity.ok().build(); // Placeholder
    }

    // DELETE /genes/{id}/functions/{functionId} → Eliminar una asociación
    @DeleteMapping("/genes/{genomeId}/{chromosomeName}/{geneSymbol}/functions/{functionId}")
    public ResponseEntity<Void> desasociarFuncionDeGen(
            @PathVariable String genomeId,
            @PathVariable String chromosomeName,
            @PathVariable String geneSymbol,
            @PathVariable String functionId) {
        // Lógica para llamar al servicio y eliminar la asociación
        return ResponseEntity.noContent().build(); // Placeholder
    }
}