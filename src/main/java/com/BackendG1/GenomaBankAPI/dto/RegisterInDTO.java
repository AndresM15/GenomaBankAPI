package com.BackendG1.GenomaBankAPI.dto;

import lombok.Data;

@Data
public class RegisterInDTO {
    private String nombre;
    private String email;
    private String password;
    // El rol no se incluye aquí, se asignará USER por defecto en el servicio.
}
