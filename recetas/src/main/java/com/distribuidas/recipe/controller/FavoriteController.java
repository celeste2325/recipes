package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.dto.response.FavoritoResponseDto;
import com.distribuidas.recipe.model.mapstruct.FavoriteMapper;
import com.distribuidas.recipe.service.interfaces.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FavoriteController {

    private final FavoriteService favoritoService;
    private final FavoriteMapper favoritoMapper;

    @GetMapping()
    public ResponseEntity<List<FavoritoResponseDto>> devolverFavoritosDeUser(@RequestParam(defaultValue = "0") Integer idUsuario) {
        return new ResponseEntity<>(this.favoritoMapper.mapLisToDto(this.favoritoService.getFavoritos(idUsuario)), HttpStatus.OK);
    }
}
