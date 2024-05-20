package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Unit;
import com.distribuidas.recipe.service.interfaces.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/units")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UnitController {
    private final UnitService unitService;

    @GetMapping
    public List<Unit> getUnits() {
        return this.unitService.getUnits();
    }

}
