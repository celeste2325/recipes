package com.distribuidas.recetas.model.dto.response;

import com.distribuidas.recetas.model.entities.Receta;
import lombok.Data;

@Data
public class FavoritoResponseDto {
    private Receta recetasByIdReceta;
}
