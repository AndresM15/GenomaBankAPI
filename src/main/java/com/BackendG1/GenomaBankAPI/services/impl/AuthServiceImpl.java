package com.BackendG1.GenomaBankAPI.services.impl;

import com.BackendG1.GenomaBankAPI.auth.JwtService;
import com.BackendG1.GenomaBankAPI.dto.AuthOutDTO;
import com.BackendG1.GenomaBankAPI.dto.LoginInDTO;
import com.BackendG1.GenomaBankAPI.dto.RegisterInDTO;
import com.BackendG1.GenomaBankAPI.entities.Usuario;
import com.BackendG1.GenomaBankAPI.enums.Role;
import com.BackendG1.GenomaBankAPI.repositories.UsuarioRepository;
import com.BackendG1.GenomaBankAPI.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthOutDTO register(RegisterInDTO req) {
        // 1. VALIDACIÓN: Comprobar si el email ya existe.
        if (usuarioRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está en uso.");
        }

        // 2. CREACIÓN: Crear la nueva entidad Usuario.
        Usuario usuario = new Usuario();
        usuario.setNombre(req.getNombre());
        usuario.setEmail(req.getEmail());
        // 3. SEGURIDAD: Encriptar la contraseña antes de guardarla.
        usuario.setPassword(passwordEncoder.encode(req.getPassword()));
        // 4. LÓGICA DE NEGOCIO: Asignar el rol por defecto.
        usuario.setRol(Role.USER);

        // 5. PERSISTENCIA: Guardar el usuario en la base de datos (Capa Repository).
        usuarioRepository.save(usuario);

        // 6. GENERACIÓN DE TOKEN: Crear un JWT para el nuevo usuario.
        String token = jwtService.generateToken(usuario);

        // 7. RESPUESTA: Devolver el token en un DTO de salida.
        return new AuthOutDTO(token);
    }

    @Override
    public AuthOutDTO login(LoginInDTO req) {
        // Autentica usando el manager de Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        // Si la autenticación es exitosa, busca el usuario y genera el token
        UserDetails user = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        String token = jwtService.generateToken(user);
        return new AuthOutDTO(token);
    }
}
