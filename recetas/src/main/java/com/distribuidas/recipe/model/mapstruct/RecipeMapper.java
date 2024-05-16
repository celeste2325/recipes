package com.distribuidas.recipe.model.mapstruct;


import com.distribuidas.recipe.model.dto.RecipeDto;
import com.distribuidas.recipe.model.dto.response.RecetaResponseDto;
import com.distribuidas.recipe.model.entities.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe mapToEntity(RecipeDto recetaDto);

    RecetaResponseDto mapResponseDto(Recipe receta);

    List<RecetaResponseDto> mapLisToDto(List<Recipe> recetaList);
}
