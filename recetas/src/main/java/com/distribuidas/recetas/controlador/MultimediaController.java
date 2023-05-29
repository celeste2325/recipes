package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import com.distribuidas.recetas.servicios.interfaces.MultimediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/multimedia")
@AllArgsConstructor
public class MultimediaController {
    private MultimediaService multimediaService;

    @PostMapping
    public ResponseEntity<?> cargarMultimedia(@RequestBody Collection<Multimedia> multimediaByIdPaso) {
        return new ResponseEntity<>(this.multimediaService.cargarMultimediasPaso(multimediaByIdPaso), HttpStatus.OK);
    }
}
