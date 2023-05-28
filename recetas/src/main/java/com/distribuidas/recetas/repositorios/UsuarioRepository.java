package com.distribuidas.recetas.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distribuidas.recetas.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByNickname(String nickname);

	Optional<Usuario> findByMail(String email);
}
