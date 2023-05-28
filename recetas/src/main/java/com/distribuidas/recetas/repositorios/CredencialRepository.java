package com.distribuidas.recetas.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distribuidas.recetas.modelo.Credencial;

public interface CredencialRepository extends JpaRepository<Credencial, Integer> {

	Optional<Credencial> findByidUsuario(int idusuario);
}
