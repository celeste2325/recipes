package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
}
