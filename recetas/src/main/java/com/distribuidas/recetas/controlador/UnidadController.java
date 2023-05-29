package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Unidad;
import com.distribuidas.recetas.servicios.interfaces.UnidadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidades")
@AllArgsConstructor
public class UnidadController {
    private final UnidadService unidadService;

    @GetMapping
    public List<Unidad> devuelveUnidades() {
        return this.unidadService.devolverUnidades();
    }

}
