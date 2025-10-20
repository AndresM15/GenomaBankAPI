package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.services.GenesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genes")
public class GenesController {
    private final GenesService genesService;


    public GenesController(GenesService genesService) {
        this.genesService = genesService;
    }

    @PostMapping
    public ResponseEntity<GeneOutDTO> crearGen(@RequestBody GeneInDTO inDTO){
        GeneOutDTO dto = this.genesService.crearGen(inDTO);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneOutDTO> consultarGen(@PathVariable Long id){
        GeneOutDTO dto = this.genesService.consultarGen(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<GeneOutDTO>> listarGenes(
            @RequestParam(required = false) Long chromosomeId,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) Integer end,
            @RequestParam(required = false) String symbol) {

        List<GeneOutDTO> genes = this.genesService.listarGenes(chromosomeId,start,end,symbol);
        return ResponseEntity.ok(genes);
    }
}
