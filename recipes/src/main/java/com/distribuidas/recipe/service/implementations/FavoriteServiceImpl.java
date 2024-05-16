package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Favorite;
import com.distribuidas.recipe.repository.FavoriteRepository;
import com.distribuidas.recipe.service.interfaces.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;
    @Override
    public List<Favorite> getFavorites(Integer userID) {
      return this.favoriteRepository.findByIdUsuario(userID);
    }
}
