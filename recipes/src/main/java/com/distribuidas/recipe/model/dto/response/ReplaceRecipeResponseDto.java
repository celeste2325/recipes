package com.distribuidas.recipe.model.dto.response;

import com.distribuidas.recipe.model.entities.Review;
import lombok.Data;

import java.util.Collection;
@Data
public class ReplaceRecipeResponseDto {
    private String nombre;
    private Collection<Review> calificacionesByIdReceta;
}
