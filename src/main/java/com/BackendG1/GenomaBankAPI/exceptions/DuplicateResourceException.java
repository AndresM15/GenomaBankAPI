package com.BackendG1.GenomaBankAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para manejar conflictos de recursos duplicados.
 * Esto automáticamente devuelve un código de estado HTTP 409 (Conflict).
 */

@ResponseStatus (value = HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
