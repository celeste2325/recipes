package com.distribuidas.recipe.model.mapstruct;


import com.distribuidas.recipe.model.dto.RecipeDto;
import com.distribuidas.recipe.model.dto.response.RecipeResponseDto;
import com.distribuidas.recipe.model.entities.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe mapToEntity(RecipeDto recipeDto);

    RecipeResponseDto mapResponseDto(Recipe recipe);

    List<RecipeResponseDto> mapLisToDto(List<Recipe> recipeList);
}
