package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credencial, Integer> {

    Optional<Credencial> findByidUsuario(int idusuario);
}
