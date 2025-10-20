package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.entities.Functions;
import java.util.List;

public interface FunctionsService {
    List<Functions> findAllFiltered(String code, String category);
    Functions findById(Long id);
}


