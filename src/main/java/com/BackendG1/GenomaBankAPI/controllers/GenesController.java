package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.services.GenesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
