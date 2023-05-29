package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNickname(String nickname);

    Optional<Usuario> findByMail(String email);
}
