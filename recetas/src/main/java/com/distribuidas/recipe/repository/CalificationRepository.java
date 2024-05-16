package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalificationRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByIdReceta(Integer idReceta);

    @Modifying
    @Query(value = "UPDATE calificaciones SET comentarios = NULL WHERE idReceta = ?1", nativeQuery = true)
    void eliminarComentariosDeReceta(Integer idReceta);
}
