package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Multimedia;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface MultimediaService {

    List<Multimedia> saveMultimediasStep(Collection<Multimedia> multimediaByStepID);
}
