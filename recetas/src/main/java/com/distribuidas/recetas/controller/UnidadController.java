package com.distribuidas.recetas.controller;

import com.distribuidas.recetas.model.entities.Unidad;
import com.distribuidas.recetas.service.interfaces.UnidadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidades")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UnidadController {
    private final UnidadService unidadService;

    @GetMapping
    public List<Unidad> devuelveUnidades() {
        return this.unidadService.devolverUnidades();
    }

}
