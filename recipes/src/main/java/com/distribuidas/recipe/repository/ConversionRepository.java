package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<Conversion, Integer> {
}
