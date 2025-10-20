package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Functions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionsRepository extends JpaRepository<Functions, Long> {
    List<Functions> findByCode(String code);
    List<Functions> findByCategory(String category);
    List<Functions> findByCodeAndCategory(String code, String category);
}
