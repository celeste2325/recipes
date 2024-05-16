package com.distribuidas.recetas.service.implementaciones;

import com.distribuidas.recetas.model.entities.Favorito;
import com.distribuidas.recetas.repository.FavoritoRepository;
import com.distribuidas.recetas.service.interfaces.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    FavoritoRepository favoritoRepository;
    @Override
    public List<Favorito> getFavoritos(Integer idUsuario) {
      return this.favoritoRepository.findByIdUsuario(idUsuario);
    }
}
