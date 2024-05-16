package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Multimedia;
import com.distribuidas.recipe.repository.MultimediaRepository;
import com.distribuidas.recipe.service.interfaces.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MultimediaServiceImpl implements MultimediaService {

    @Autowired
    MultimediaRepository multimediaRepository;

    @Override
    public List<Multimedia> saveMultimediasStep(Collection<Multimedia> multimediaByStepID) {
        return this.multimediaRepository.saveAll(multimediaByStepID);
    }
}
