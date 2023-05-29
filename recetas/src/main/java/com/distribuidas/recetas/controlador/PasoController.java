package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import com.distribuidas.recetas.modelo.entities.Paso;
import com.distribuidas.recetas.servicios.interfaces.PasoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pasos")
@AllArgsConstructor
public class PasoController {
    private final PasoService pasoService;

    @PostMapping
    public ResponseEntity<Paso> cargarMultimedia(@RequestBody Collection<Multimedia> multimediaByIdPaso) {
        return new ResponseEntity<>(this.pasoService.cargarMultimediasPaso(multimediaByIdPaso), HttpStatus.OK);
    }

    @GetMapping("{idReceta}")
    public ResponseEntity<?> devolverPasosDeReceta(@PathVariable Integer idReceta) {
        return new ResponseEntity<>(this.pasoService.buscaTodosLosPasosDeUnaReceta(idReceta), HttpStatus.OK);
    }


}
