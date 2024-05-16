package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredencialRepository extends JpaRepository<Credencial, Integer> {

    Optional<Credencial> findByidUsuario(int idusuario);
}
