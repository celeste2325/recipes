package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Calificacion;

import java.util.List;

public interface CalificacionService {

    Calificacion calificarReceta(Calificacion newCalificacion);

    List<Calificacion> devolverCalificacionesByIdReceta(Integer idReceta);

    void eliminarCalificacionDeReceta(Calificacion calificacion);
}
