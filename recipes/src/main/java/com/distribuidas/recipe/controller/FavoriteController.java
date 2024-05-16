package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.dto.response.FavoriteResponseDto;
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

    private final FavoriteService favoriteService;
    private final FavoriteMapper favoriteMapper;

    @GetMapping()
    public ResponseEntity<List<FavoriteResponseDto>> getFavoritesByUser(@RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.favoriteMapper.mapLisToDto(this.favoriteService.getFavorites(userID)), HttpStatus.OK);
    }
}
