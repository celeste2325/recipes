package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Favorito;
import com.distribuidas.recetas.repositorios.FavoritoRepository;
import com.distribuidas.recetas.servicios.interfaces.FavoritoService;
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
