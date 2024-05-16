package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Rating;
import com.distribuidas.recipe.service.interfaces.CalificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CalificationController {
    private CalificationService calificacionService;

    @PostMapping
    public ResponseEntity<Rating> calificarReceta(@RequestBody Rating newCalificacion) {
        return new ResponseEntity<>(this.calificacionService.calificarReceta(newCalificacion), HttpStatus.CREATED);
    }
//TODO hacer vista q solo devuelva los autorizados
    @GetMapping("{idReceta}")
    public ResponseEntity<List<Rating>> consultarCalificacionesDeReceta(@PathVariable Integer idReceta) {
        return new ResponseEntity<>(this.calificacionService.devolverCalificacionesByIdReceta(idReceta), HttpStatus.OK);
    }

    @DeleteMapping("{calificacionId}")
    public void eliminarCalificacion(@PathVariable Integer calificacionId) {
        this.calificacionService.eliminarCalificacionDeReceta(calificacionId);
    }

}
