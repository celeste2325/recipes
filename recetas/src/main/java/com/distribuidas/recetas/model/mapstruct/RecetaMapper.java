package com.distribuidas.recetas.model.mapstruct;


import com.distribuidas.recetas.model.dto.RecetaDto;
import com.distribuidas.recetas.model.dto.response.RecetaResponseDto;
import com.distribuidas.recetas.model.entities.Receta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecetaMapper {
    Receta mapToEntity(RecetaDto recetaDto);

    RecetaResponseDto mapResponseDto(Receta receta);

    List<RecetaResponseDto> mapLisToDto(List<Receta> recetaList);
}
