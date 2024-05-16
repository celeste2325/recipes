package com.distribuidas.recetas.controller;

import com.distribuidas.recetas.model.dto.response.FavoritoResponseDto;
import com.distribuidas.recetas.model.mapstruct.FavoritoMapper;
import com.distribuidas.recetas.service.interfaces.FavoritoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService favoritoService;
    private final FavoritoMapper favoritoMapper;

    @GetMapping()
    public ResponseEntity<List<FavoritoResponseDto>> devolverFavoritosDeUser(@RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.favoritoMapper.mapLisToDto(this.favoritoService.getFavoritos(idUsuario)), HttpStatus.OK);
    }
}
