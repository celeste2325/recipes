package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {
}
