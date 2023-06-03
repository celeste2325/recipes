package com.distribuidas.recetas.modelo.dto.response;

import com.distribuidas.recetas.modelo.entities.Calificacion;
import lombok.Data;

import java.util.Collection;

@Data
public class ReemplazarRecetaResponseDto {
    private String nombre;
    private Collection<Calificacion> calificacionesByIdReceta;
}
