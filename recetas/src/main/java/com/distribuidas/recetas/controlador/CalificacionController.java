package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Calificacion;
import com.distribuidas.recetas.servicios.interfaces.CalificacionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@AllArgsConstructor
public class CalificacionController {
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<Calificacion> calificarReceta(@RequestBody Calificacion newCalificacion) {
        return new ResponseEntity<>(this.calificacionService.calificarReceta(newCalificacion), HttpStatus.CREATED);
    }
//TODO hacer vista q solo devuelva los autorizados
    @GetMapping("{idReceta}")
    public ResponseEntity<List<Calificacion>> consultarCalificacionesDeReceta(@PathVariable Integer idReceta) {
        return new ResponseEntity<>(this.calificacionService.devolverCalificacionesByIdReceta(idReceta), HttpStatus.OK);
    }

    @DeleteMapping("{idReceta}")
    public void eliminarCalificacion(@PathVariable Calificacion calificacion) {
        this.calificacionService.eliminarCalificacionDeReceta(calificacion);
    }

}
