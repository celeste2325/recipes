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

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<?> createIngredient(@RequestBody Ingredient newIngredient) {
        try {
            return new ResponseEntity<>(this.ingredientService.saveIngredient(newIngredient), HttpStatus.CREATED);
        } catch (ExistingIngredientException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public List<Ingredient> getIngredients() {
        return this.ingredientService.getIngredients();
    }

    @GetMapping("partialSearch/{partialNameIngredient}")
    public List<Ingredient> getTypeByPartialName(@PathVariable String partialNameIngredient) {
        if (partialNameIngredient.length() >= 2) {
            return this.ingredientService.getIngredientsByPartialName(partialNameIngredient);
        }
        return null;
    }
}
