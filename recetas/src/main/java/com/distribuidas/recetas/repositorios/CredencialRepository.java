package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Credencial;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialRepository extends JpaRepository<Credencial,Integer> {
	
	 Optional<Credencial> findByidUsuario(int idusuario);
}
