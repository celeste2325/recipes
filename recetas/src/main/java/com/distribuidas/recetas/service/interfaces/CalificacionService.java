package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.model.entities.Calificacion;

import java.util.List;

public interface CalificacionService {

    Calificacion calificarReceta(Calificacion newCalificacion);

    List<Calificacion> devolverCalificacionesByIdReceta(Integer idReceta);

    void eliminarCalificacionDeReceta(Integer calificacionId);
}
