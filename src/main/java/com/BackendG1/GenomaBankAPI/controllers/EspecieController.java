package com.BackendG1.GenomaBankAPI.controllers;
import com.BackendG1.GenomaBankAPI.dto.EspecieInDTO;
import com.BackendG1.GenomaBankAPI.dto.EspecieOutDTO;
import com.BackendG1.GenomaBankAPI.services.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class EspecieController {

    private final EspecieService especieService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<EspecieOutDTO> getAllSpecies() {
        return especieService.listSpecies();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public EspecieOutDTO getSpeciesById(@PathVariable Long id) {
        return especieService.findSpeciesById(id);
    }

    /**
     * Endpoint para crear una nueva especie.
     * Protegido: Solo accesible por usuarios con rol ADMIN.
     * @param dto El DTO con los datos de la nueva especie.
     * @return El DTO de la especie creada, con código 201.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')") // <<< ¡LA SEGURIDAD!
    public EspecieOutDTO createSpecies(@RequestBody EspecieInDTO dto) {
        return especieService.createSpecies(dto);
    }

    /**
     * Endpoint para actualizar una especie existente.
     * Protegido: Solo accesible por usuarios con rol ADMIN.
     * @param id El ID de la especie a actualizar.
     * @param dto El DTO con los nuevos datos.
     * @return El DTO de la especie ya actualizada.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EspecieOutDTO updateSpecies(@PathVariable Long id, @RequestBody EspecieInDTO dto) {
        return especieService.updateSpecies(id, dto);
    }

    /**
     * Endpoint para eliminar una especie.
     * Protegido: Solo accesible por usuarios con rol ADMIN.
     * @param id El ID de la especie a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Devuelve 204 si tiene éxito
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSpecies(@PathVariable Long id) {
        especieService.deleteSpecies(id);
    }
}