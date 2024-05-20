package com.distribuidas.recipe.model.mapstruct;

import com.distribuidas.recipe.model.dto.FavoriteDto;
import com.distribuidas.recipe.model.dto.response.FavoriteResponseDto;
import com.distribuidas.recipe.model.entities.Favorite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    Favorite mapToEntity(FavoriteDto favoriteDto);

    FavoriteResponseDto mapResponseDto(Favorite favorite);

    List<FavoriteResponseDto> mapLisToDto(List<Favorite> favorites);
}
