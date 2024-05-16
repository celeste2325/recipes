package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Rating;

import java.util.List;

public interface CalificationService {

    Rating calificarReceta(Rating newCalificacion);

    List<Rating> devolverCalificacionesByIdReceta(Integer idReceta);

    void eliminarCalificacionDeReceta(Integer calificacionId);
}
