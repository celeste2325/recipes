package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito,Integer> {
}
