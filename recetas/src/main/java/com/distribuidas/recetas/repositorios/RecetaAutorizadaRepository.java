package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.modelo.entities.RecetaAutorizada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaAutorizadaRepository extends JpaRepository<RecetaAutorizada, Integer> {
    RecetaAutorizada findByNombreAndIdUsuario(String nombre, Integer idUsuario);

    List<RecetaAutorizada> findByNombre(String nombreReceta);

    List<RecetaAutorizada> findByIdTipo(Integer idTipo);
    List<RecetaAutorizada> findByIdUsuario(Integer idUsuario);

    List<RecetaAutorizada> findByNombreLikeIgnoreCase(String s);
}
