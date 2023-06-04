package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findByIdReceta(Integer idReceta);

    @Modifying
    @Query(value = "UPDATE calificaciones SET comentarios = NULL WHERE idReceta = ?1", nativeQuery = true)
    void eliminarComentariosDeReceta(Integer idReceta);
}
