package com.distribuidas.recipe.model.dto;
import com.distribuidas.recipe.model.entities.Review;
import lombok.Data;

import java.util.Collection;

@Data
public class ReplaceRecipeResponseDto {
    private String name;
    private Collection<Review> reviewByRecipeID;
}
