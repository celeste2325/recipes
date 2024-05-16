package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Paso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasoRepository extends JpaRepository<Paso, Integer> {
    List<Paso> findByIdReceta(Integer idReceta);
}
