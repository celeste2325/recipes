package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
}
