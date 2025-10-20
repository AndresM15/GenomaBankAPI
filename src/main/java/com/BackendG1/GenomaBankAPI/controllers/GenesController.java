package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.GeneInDTO;
import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.SequenceDTO;
import com.BackendG1.GenomaBankAPI.DTO.UpdateGeneDTO;
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

    //@PreAuthorize("hasRole('ADMIN'))
    @PostMapping
    public ResponseEntity<GeneOutDTO> crearGen(@RequestBody GeneInDTO inDTO){
        GeneOutDTO dto = this.genesService.crearGen(inDTO);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('USER'))
    @GetMapping("/{id}")
    public ResponseEntity<GeneOutDTO> consultarGen(@PathVariable Long id){
        GeneOutDTO dto = this.genesService.consultarGen(id);

        return ResponseEntity.ok(dto);
    }

    //@PreAuthorize("hasRole('USER'))
    @GetMapping
    public ResponseEntity<List<GeneOutDTO>> listarGenes(
            @RequestParam(required = false) Long chromosomeId,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) Integer end,
            @RequestParam(required = false) String symbol) {

        List<GeneOutDTO> genes = this.genesService.listarGenes(chromosomeId,start,end,symbol);
        return ResponseEntity.ok(genes);
    }

    //@PreAuthorize("hasRole('USER'))
    @GetMapping("/{id}/sequence")
    public ResponseEntity<String> obtenerSecuencia(@PathVariable Long id){
        String secuencia = this.genesService.obtenerSecuencia(id);
        return ResponseEntity.ok(secuencia);
    }

    //@PreAuthorize("hasRole('ADMIN'))
    @PutMapping("/{id}")
    public ResponseEntity<GeneOutDTO> actualizarGen(@PathVariable Long id, @RequestBody UpdateGeneDTO inDTO){
        GeneOutDTO dto = this.genesService.actualizarGen(id,inDTO);

        return ResponseEntity.ok(dto);
    }

    //@PreAuthorize("hasRole('ADMIN'))
    @PutMapping("/{id}/sequence")
    public ResponseEntity<GeneOutDTO> actualizarSecuencia(
            @PathVariable Long id,
            @RequestBody SequenceDTO body){

        GeneOutDTO dto = this.genesService.actualizarSecuencia(id,body.getSequence());
        return ResponseEntity.ok(dto);
    }

    //@PreAuthorize("hasRole('ADMIN'))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGen(@PathVariable Long id){
        this.genesService.eliminarGen(id);

        return ResponseEntity.noContent().build();
    }
}
