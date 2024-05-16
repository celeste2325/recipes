package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {
    List<Step> findByIdReceta(Integer idReceta);
}
