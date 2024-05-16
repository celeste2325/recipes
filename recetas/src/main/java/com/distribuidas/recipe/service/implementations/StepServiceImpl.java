package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Step;
import com.distribuidas.recipe.repository.StepRepository;
import com.distribuidas.recipe.service.interfaces.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepServiceImpl implements StepService {
    @Autowired
    private StepRepository pasoRepository;

    @Override
    public List<Step> buscaTodosLosPasosDeUnaReceta(Integer idReceta) {
        return this.pasoRepository.findByIdReceta(idReceta);
    }
}
