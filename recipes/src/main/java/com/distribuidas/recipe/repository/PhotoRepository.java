package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
