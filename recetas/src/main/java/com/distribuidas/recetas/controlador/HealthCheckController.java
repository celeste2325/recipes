package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class HealthCheckController {


    @GetMapping()
    public ResponseEntity<?> healtcheck() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
