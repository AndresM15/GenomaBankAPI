package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.dto.FuncionBiologicaDTO;
import com.BackendG1.GenomaBankAPI.services.IFuncionBiologicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class FuncionBiologicaController {

    private final IFuncionBiologicaService service;

    public FuncionBiologicaController(IFuncionBiologicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FuncionBiologicaDTO>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionBiologicaDTO> buscarPorId(@PathVariable long id) {
        FuncionBiologicaDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FuncionBiologicaDTO> crear(@RequestBody FuncionBiologicaDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionBiologicaDTO> actualizar(@PathVariable long id, @RequestBody FuncionBiologicaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
