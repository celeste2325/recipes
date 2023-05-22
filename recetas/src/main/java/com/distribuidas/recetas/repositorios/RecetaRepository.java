package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta,Integer> {
}
