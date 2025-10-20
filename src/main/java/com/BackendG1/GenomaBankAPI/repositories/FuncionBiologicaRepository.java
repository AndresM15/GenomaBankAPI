package com.BackendG1.GenomaBankAPI.repositories;


import com.BackendG1.GenomaBankAPI.entities.FuncionBiologica;
import com.BackendG1.GenomaBankAPI.enums.FunctionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionBiologicaRepository extends JpaRepository<FuncionBiologica, Long> {

    /**
     * Requisito: Listar/Filtrar por código.
     * @param codigo Código único de la función (ej: GO:0003700).
     * @return La función si existe.
     */
    Optional<FuncionBiologica> findByCodigo(String codigo);

    /**
     * Requisito: Listar/Filtrar por categoría.
     * @param categoria Categoría del ENUM (BP, MF, CC).
     * @return Lista de funciones en esa categoría.
     */
    List<FuncionBiologica> findByCategoria(FunctionCategory categoria);
}
