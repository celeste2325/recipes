package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.UnitOfMeasurement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnitOfMeasurementService {

    List<UnitOfMeasurement> devolverUnidades();
}
