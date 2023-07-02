package com.distribuidas.recetas.modelo.dto.response;

import com.distribuidas.recetas.modelo.entities.Receta;
import lombok.Data;

@Data
public class FavoritoResponseDto {
    private Receta recetasByIdReceta;
}
