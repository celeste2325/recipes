package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
