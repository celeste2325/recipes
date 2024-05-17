package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    Optional<Credential> findByUserID(int userID);
}
