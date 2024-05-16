package com.distribuidas.recetas.model.mapstruct;

import com.distribuidas.recetas.model.dto.FavoritoDto;
import com.distribuidas.recetas.model.dto.response.FavoritoResponseDto;
import com.distribuidas.recetas.model.entities.Favorito;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoritoMapper {
    Favorito mapToEntity(FavoritoDto favoritoDto);

    FavoritoResponseDto mapResponseDto(Favorito favorito);

    List<FavoritoResponseDto> mapLisToDto(List<Favorito> favoritos);
}
