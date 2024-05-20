package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Unit;
import com.distribuidas.recipe.repository.UnitRepository;
import com.distribuidas.recipe.service.interfaces.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitOfMeasurementRepository;

    @Override
    public List<Unit> getUnits() {
        return this.unitOfMeasurementRepository.findAll();
    }
}
