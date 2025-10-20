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
        if (usuarioRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está en uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(req.getNombre());
        usuario.setEmail(req.getEmail());
        usuario.setPassword(passwordEncoder.encode(req.getPassword()));
        usuario.setRol(Role.USER);

        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuario);
        return new AuthOutDTO(token); // Usamos el constructor normal para evitar problemas con Builder
    }

    @Override
    public AuthOutDTO login(LoginInDTO req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        UserDetails user = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        String token = jwtService.generateToken(user);
        return new AuthOutDTO(token); // Usamos el constructor normal
    }
}