package com.distribuidas.recetas.modelo.mapstruct;

import com.distribuidas.recetas.modelo.dto.RecetaDto;
import com.distribuidas.recetas.modelo.dto.response.RecetaResponseDto;
import com.distribuidas.recetas.modelo.entities.Receta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecetaMapper {
    Receta mapToEntity(RecetaDto recetaDto);

    RecetaResponseDto mapResponseDto(Receta receta);

    List<RecetaResponseDto> mapLisToDto(List<Receta> recetaList);
}
