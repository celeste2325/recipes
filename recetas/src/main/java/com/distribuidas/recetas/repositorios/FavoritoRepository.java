package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByIdUsuario(Integer idUsuario);
}
