package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
}
