package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.ChromosomeOutDTO;
import com.BackendG1.GenomaBankAPI.services.IChromosomesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chromosomes")
public class ChromosomesController {

    private final IChromosomesService ichromosomesService;

    public ChromosomesController(IChromosomesService chromosomesService) {
        this.ichromosomesService = chromosomesService;

    }

    @GetMapping
    public ResponseEntity<List<ChromosomeOutDTO>> listarCromosomas(
            @RequestParam(required = false) Long genomeId) {

        List<ChromosomeOutDTO> cromosomas = chromosomesService.listarCromosomas(genomeId);
        return ResponseEntity.ok(cromosomas);
    }
}
