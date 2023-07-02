package com.distribuidas.recetas.modelo.mapstruct;

import com.distribuidas.recetas.modelo.dto.FavoritoDto;
import com.distribuidas.recetas.modelo.dto.response.FavoritoResponseDto;
import com.distribuidas.recetas.modelo.entities.Favorito;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoritoMapper {
    Favorito mapToEntity(FavoritoDto favoritoDto);

    FavoritoResponseDto mapResponseDto(Favorito favorito);

    List<FavoritoResponseDto> mapLisToDto(List<Favorito> favoritos);
}
