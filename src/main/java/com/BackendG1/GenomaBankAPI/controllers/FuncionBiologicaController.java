package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.FuncionBiologicaDTO;
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
        return ResponseEntity.ok(service.listarFunciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionBiologicaDTO> buscarPorId(@PathVariable String id) {
        FuncionBiologicaDTO dto = service.buscarPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FuncionBiologicaDTO> crear(@RequestBody FuncionBiologicaDTO dto) {
        return ResponseEntity.ok(service.crearFuncion(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionBiologicaDTO> actualizar(@PathVariable String id, @RequestBody FuncionBiologicaDTO dto) {
        return ResponseEntity.ok(service.actualizarFuncion(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminarFuncion(id);
        return ResponseEntity.noContent().build();
    }
}
