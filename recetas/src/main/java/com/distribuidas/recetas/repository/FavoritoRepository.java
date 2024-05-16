package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByIdUsuario(Integer idUsuario);
}
