package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Favorite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteService {
    List<Favorite> getFavoritos(Integer idUsuario);
}
