package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    Ingrediente findByNombre(String nombre);

    List<Ingrediente> findByNombreLikeIgnoreCase(String s);
}
