package com.distribuidas.recetas.controller;

import com.distribuidas.recetas.model.entities.Tipo;
import com.distribuidas.recetas.service.interfaces.TipoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TipoController {
    private final TipoService tipoService;

    @GetMapping()
    public ResponseEntity<List<Tipo>> devuelveLosTiposDePlatos() {
        return new ResponseEntity<>(this.tipoService.devolverTiposPlatos(), HttpStatus.OK);
    }

    @GetMapping("busquedaParcial/{nombreParcialTipoPlato}")
    public List<Tipo> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialTipoPlato) {
        if (nombreParcialTipoPlato.length() >= 2) {
            return this.tipoService.devolverPlatosPorBusquedaParcialTipoDePlato(nombreParcialTipoPlato);
        }
        return null;//agregar excepcion
    }
}
