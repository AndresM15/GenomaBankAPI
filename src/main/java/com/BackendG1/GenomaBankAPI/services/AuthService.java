package com.BackendG1.GenomaBankAPI.services;

import com.BackendG1.GenomaBankAPI.dto.AuthOutDTO;
import com.BackendG1.GenomaBankAPI.dto.LoginInDTO;
import com.BackendG1.GenomaBankAPI.dto.RegisterInDTO;

/**
 * Interfaz que define la lógica de negocio para la autenticación.
 */
public interface AuthService {

    AuthOutDTO register(RegisterInDTO req);
    AuthOutDTO login(LoginInDTO req);
}
