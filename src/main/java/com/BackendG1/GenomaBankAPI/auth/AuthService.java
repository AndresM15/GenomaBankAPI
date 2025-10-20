package com.BackendG1.GenomaBankAPI.auth;

import com.BackendG1.GenomaBankAPI.dto.AuthOutDTO;
import com.BackendG1.GenomaBankAPI.dto.LoginInDTO;
import com.BackendG1.GenomaBankAPI.dto.RegisterInDTO;
import com.BackendG1.GenomaBankAPI.entities.Usuario;
import com.BackendG1.GenomaBankAPI.enums.Role;
import com.BackendG1.GenomaBankAPI.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthOutDTO login(LoginInDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Usuario user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);

        return AuthOutDTO.builder()
                .accessToken(token)
                .build();
    }

    public AuthOutDTO register(RegisterInDTO request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRol(Role.USER); // Asignamos USER por defecto como pide el plan

        usuarioRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthOutDTO.builder()
                .accessToken(token)
                .build();
    }
}
