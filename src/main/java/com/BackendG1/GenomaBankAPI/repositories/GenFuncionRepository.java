package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.GenFuncion;
import com.BackendG1.GenomaBankAPI.entities.GenFuncionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenFuncionRepository extends JpaRepository<GenFuncion, GenFuncionId> {

    /**
     * Requisito: Listar todas las funciones asociadas a un gen específico.
     * Corresponde a GET /genes/{id}/functions
     * @param genId ID del gen.
     * @return Lista de objetos GenFuncion (la tabla de unión).
     */
    List<GenFuncion> findByIdGenId(Long genId);

    /**
     * Requisito: Eliminar una asociación específica.
     * Corresponde a DELETE /genes/{id}/functions/{functionId}
     * Se usa para verificar si la asociación ya existe antes de crearla o antes de eliminarla.
     * @param genId ID del gen.
     * @param funcionId ID de la función.
     * @return La asociación si existe.
     */
    Optional<GenFuncion> findByIdGenIdAndIdFuncionId(Long genId, Long funcionId);
}
