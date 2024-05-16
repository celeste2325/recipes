package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    List<Tipo> findByDescripcionLikeIgnoreCase(String s);
}
