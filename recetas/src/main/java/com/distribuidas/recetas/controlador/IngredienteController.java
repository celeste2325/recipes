package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.excepciones.ElIngredienteYaExisteException;
import com.distribuidas.recetas.modelo.entities.Ingrediente;
import com.distribuidas.recetas.servicios.interfaces.IngredienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@AllArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<?> crearIngrediente(@RequestBody Ingrediente newIngrediente) {
        try {
            return new ResponseEntity<>(this.ingredienteService.salvarIngrediente(newIngrediente), HttpStatus.CREATED);
        } catch (ElIngredienteYaExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public List<Ingrediente> devuelveIngredientes() {
        return this.ingredienteService.listarIngredientes();
    }

    @GetMapping("busquedaParcial/{nombreParcialIngrediente}")
    public List<Ingrediente> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialIngrediente) {
        if (nombreParcialIngrediente.length() >= 3) {
            return this.ingredienteService.devolverIngredientesPorBusquedaParcial(nombreParcialIngrediente);
        }
        return null;
    }
}
