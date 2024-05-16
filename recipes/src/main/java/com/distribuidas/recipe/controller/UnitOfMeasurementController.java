package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.UnitOfMeasurement;
import com.distribuidas.recipe.service.interfaces.UnitOfMeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidades")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UnitOfMeasurementController {
    private final UnitOfMeasurementService unitOfMeasurementService;

    @GetMapping
    public List<UnitOfMeasurement> getUnits() {
        return this.unitOfMeasurementService.getUnits();
    }

}
