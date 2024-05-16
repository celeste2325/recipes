package com.distribuidas.recetas.controller;

import com.distribuidas.recetas.service.interfaces.PasoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pasos")
@AllArgsConstructor
public class PasoController {
    private final PasoService pasoService;

    @GetMapping("{idReceta}")
    public ResponseEntity<?> devolverPasosDeReceta(@PathVariable Integer idReceta) {
        return new ResponseEntity<>(this.pasoService.buscaTodosLosPasosDeUnaReceta(idReceta), HttpStatus.OK);
    }


}
