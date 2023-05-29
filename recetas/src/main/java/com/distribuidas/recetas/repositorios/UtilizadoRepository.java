package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Utilizado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadoRepository extends JpaRepository<Utilizado, Integer> {
}
