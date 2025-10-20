package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Genoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenRepository extends JpaRepository<Genoma, GenId> {

    // Para el endpoint de análisis
    @Query("SELECT g FROM Genoma g WHERE g.id.idGenoma = :genomeId AND g.id.nombreCromosoma = :chromosomeName AND g.posicionInicio >= :start AND g.posicionFinal <= :end")
    List<Genoma> findByChromosomeAndRange(
            @Param("genomeId") String genomeId,
            @Param("chromosomeName") String chromosomeName,
            @Param("start") int start,
            @Param("end") int end
    );
}
