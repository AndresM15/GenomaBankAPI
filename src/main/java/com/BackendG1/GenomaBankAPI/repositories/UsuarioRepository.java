package com.BackendG1.GenomaBankAPI.repositories;

import com.BackendG1.GenomaBankAPI.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su correo electrónico.
     * Es crucial para la autenticación (login).
     * @param email El correo electrónico a buscar.
     * @return Un Optional que contiene el usuario si se encuentra.
     */
    Optional<Usuario> findByEmail(String email);
}
