package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findByDescripcionLikeIgnoreCase(String s);
}
