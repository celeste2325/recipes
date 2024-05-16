package com.distribuidas.recipe.model.mapstruct;

import com.distribuidas.recipe.model.dto.FavoriteDto;
import com.distribuidas.recipe.model.dto.response.FavoritoResponseDto;
import com.distribuidas.recipe.model.entities.Favorite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    Favorite mapToEntity(FavoriteDto favoritoDto);

    FavoritoResponseDto mapResponseDto(Favorite favorito);

    List<FavoritoResponseDto> mapLisToDto(List<Favorite> favoritos);
}
