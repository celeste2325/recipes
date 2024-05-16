package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.model.entities.Favorito;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoritoService {
    List<Favorito> getFavoritos(Integer idUsuario);
}
