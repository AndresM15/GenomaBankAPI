package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.DTO.GeneOutDTO;
import com.BackendG1.GenomaBankAPI.DTO.UpdateGeneDTO;
import com.BackendG1.GenomaBankAPI.entities.Genes;
import com.BackendG1.GenomaBankAPI.services.GenesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genes")
public class GenesController {

    private final GenesService genesService;

    public GenesController(GenesService genesService) {
        this.genesService = genesService;
    }

    // ✅ GET /genes/{id}/sequence
    @GetMapping("/{id}/sequence")
    public ResponseEntity<String> getSequence(@PathVariable Long id) {
        return ResponseEntity.ok(genesService.findById(id).getSequence());
    }

    // ✅ PUT /genes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<GeneOutDTO> updateGene(@PathVariable Long id, @RequestBody UpdateGeneDTO dto) {
        Genes gene = new Genes();
        gene.setSymbol(dto.getSymbol());
        gene.setStartPos(dto.getStartPos());
        gene.setEndPos(dto.getEndPos());
        gene.setStrand(dto.getStrand());
        gene.setSequence(dto.getSequence());
        Genes updated = genesService.updateGene(id, gene);

        GeneOutDTO out = new GeneOutDTO();
        out.setId(updated.getId());
        out.setSymbol(updated.getSymbol());
        out.setStartPos(updated.getStartPos());
        out.setEndPos(updated.getEndPos());
        out.setStrand(updated.getStrand());
        out.setSequence(updated.getSequence());
        return ResponseEntity.ok(out);
    }

    // ✅ DELETE /genes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGene(@PathVariable Long id) {
        genesService.deleteGene(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ PUT /genes/{id}/sequence
    @PutMapping("/{id}/sequence")
    public ResponseEntity<String> updateSequence(@PathVariable Long id, @RequestBody String newSequence) {
        Genes gene = genesService.findById(id);
        gene.setSequence(newSequence);
        genesService.updateGene(id, gene);
        return ResponseEntity.ok("Secuencia actualizada correctamente.");
    }
}