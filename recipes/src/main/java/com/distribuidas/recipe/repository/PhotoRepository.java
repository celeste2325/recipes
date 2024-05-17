package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.PhotoInstruction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoInstruction, Integer> {
}
