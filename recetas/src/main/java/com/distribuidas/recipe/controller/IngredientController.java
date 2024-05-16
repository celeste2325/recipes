package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.exception.ExistingIngredientException;
import com.distribuidas.recipe.model.entities.Ingredient;
import com.distribuidas.recipe.service.interfaces.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class IngredientController {

    private final IngredientService ingredienteService;

    @PostMapping
    public ResponseEntity<?> crearIngrediente(@RequestBody Ingredient newIngrediente) {
        try {
            return new ResponseEntity<>(this.ingredienteService.salvarIngrediente(newIngrediente), HttpStatus.CREATED);
        } catch (ExistingIngredientException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public List<Ingredient> devuelveIngredientes() {
        return this.ingredienteService.listarIngredientes();
    }

    @GetMapping("busquedaParcial/{nombreParcialIngrediente}")
    public List<Ingredient> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialIngrediente) {
        if (nombreParcialIngrediente.length() >= 2) {
            return this.ingredienteService.devolverIngredientesPorBusquedaParcial(nombreParcialIngrediente);
        }
        return null;
    }
}
