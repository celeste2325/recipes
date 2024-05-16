package com.distribuidas.recipe.model.dto.response;

import com.distribuidas.recipe.model.entities.Recipe;
import lombok.Data;

@Data
public class FavoritoResponseDto {
    private Recipe recetasByIdReceta;
}
