package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.UnitOfMeasurement;
import com.distribuidas.recipe.repository.UnitOfMeasurementRepository;
import com.distribuidas.recipe.service.interfaces.UnitOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasurementServiceImpl implements UnitOfMeasurementService {
    @Autowired
    private UnitOfMeasurementRepository unitOfMeasurementRepository;

    @Override
    public List<UnitOfMeasurement> getUnits() {
        return this.unitOfMeasurementRepository.findAll();
    }
}
