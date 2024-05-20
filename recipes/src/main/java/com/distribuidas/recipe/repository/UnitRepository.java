package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
