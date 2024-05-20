package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Unit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnitService {

    List<Unit> getUnits();
}
