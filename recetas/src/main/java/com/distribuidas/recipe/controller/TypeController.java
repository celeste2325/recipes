package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Type;
import com.distribuidas.recipe.service.interfaces.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TypeController {
    private final TypeService tipoService;

    @GetMapping()
    public ResponseEntity<List<Type>> devuelveLosTiposDePlatos() {
        return new ResponseEntity<>(this.tipoService.devolverTiposPlatos(), HttpStatus.OK);
    }

    @GetMapping("busquedaParcial/{nombreParcialTipoPlato}")
    public List<Type> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialTipoPlato) {
        if (nombreParcialTipoPlato.length() >= 2) {
            return this.tipoService.devolverPlatosPorBusquedaParcialTipoDePlato(nombreParcialTipoPlato);
        }
        return null;//agregar excepcion
    }
}
