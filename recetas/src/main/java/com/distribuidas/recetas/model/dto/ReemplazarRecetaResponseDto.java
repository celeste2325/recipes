package com.distribuidas.recetas.model.dto;
import com.distribuidas.recetas.model.entities.Calificacion;
import lombok.Data;

import java.util.Collection;

@Data
public class ReemplazarRecetaResponseDto {
    private String nombre;
    private Collection<Calificacion> calificacionesByIdReceta;
}
