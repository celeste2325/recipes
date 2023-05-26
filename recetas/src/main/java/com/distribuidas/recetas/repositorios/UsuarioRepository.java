package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	 Optional<Usuario> findByNickname(String nickname);
}
