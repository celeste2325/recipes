package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Category;
import com.distribuidas.recipe.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("partialSearch/{categoryPartialName}")
    public List<Category> getCategoryByPartialName(@PathVariable String categoryPartialName) {
        if (categoryPartialName.length() >= 2) {
            return this.categoryService.getCategoryByPartialName(categoryPartialName);
        }
        return null;
    }
}
