package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.FunctionOutDTO;
import com.BackendG1.GenomaBankAPI.entities.Functions;
import com.BackendG1.GenomaBankAPI.services.FunctionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/functions")
public class FunctionsController {

    private final FunctionsService functionsService;

    public FunctionsController(FunctionsService functionsService) {
        this.functionsService = functionsService;
    }

    // ✅ GET /functions?code=&category=
    @GetMapping
    public ResponseEntity<List<FunctionOutDTO>> listFunctions(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String category) {

        List<FunctionOutDTO> dtos = functionsService.findAllFiltered(code, category)
                .stream()
                .map(f -> {
                    FunctionOutDTO dto = new FunctionOutDTO();
                    dto.setId(f.getId());
                    dto.setCode(f.getCode());
                    dto.setName(f.getName());
                    dto.setCategory(f.getCategory());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // ✅ GET /functions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FunctionOutDTO> getFunction(@PathVariable Long id) {
        Functions f = functionsService.findById(id);
        FunctionOutDTO dto = new FunctionOutDTO();
        dto.setId(f.getId());
        dto.setCode(f.getCode());
        dto.setName(f.getName());
        dto.setCategory(f.getCategory());
        return ResponseEntity.ok(dto);
    }
}