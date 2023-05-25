package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion,Integer> {
}
