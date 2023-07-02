package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Favorito;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoritoService {
    List<Favorito> getFavoritos(Integer idUsuario);
}
