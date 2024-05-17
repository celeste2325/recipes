package com.distribuidas.recipe.model.dto.response;

import com.distribuidas.recipe.model.entities.Rating;
import lombok.Data;

import java.util.Collection;
@Data
public class ReplaceRecipeResponseDto {
    private String nombre;
    private Collection<Rating> calificacionesByIdReceta;
}