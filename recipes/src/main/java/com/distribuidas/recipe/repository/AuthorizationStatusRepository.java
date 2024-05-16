package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.AuthorizationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationStatusRepository extends JpaRepository<AuthorizationStatus, Integer> {
}
