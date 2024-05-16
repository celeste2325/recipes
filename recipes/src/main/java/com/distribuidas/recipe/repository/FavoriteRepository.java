package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByIdUsuario(Integer idUsuario);
}
