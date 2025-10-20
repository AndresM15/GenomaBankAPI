package com.BackendG1.GenomaBankAPI.controllers;

import com.BackendG1.GenomaBankAPI.dto.AuthOutDTO;
import com.BackendG1.GenomaBankAPI.dto.LoginInDTO;
import com.BackendG1.GenomaBankAPI.dto.RegisterInDTO;
import com.BackendG1.GenomaBankAPI.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // Inyectamos la interfaz del servicio, no la implementación.
    private final AuthService authService;

    /**
     * Endpoint para registrar un nuevo usuario.
     * @param req El DTO con los datos de registro.
     * @return El DTO con el token JWT.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // Devuelve un código 201 Created si es exitoso.
    public AuthOutDTO register(@RequestBody RegisterInDTO req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public AuthOutDTO login(@RequestBody LoginInDTO req) {
        return authService.login(req);
    }
}
