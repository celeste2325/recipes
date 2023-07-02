package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.dto.response.FavoritoResponseDto;
import com.distribuidas.recetas.modelo.entities.Favorito;
import com.distribuidas.recetas.modelo.mapstruct.FavoritoMapper;
import com.distribuidas.recetas.servicios.interfaces.FavoritoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
