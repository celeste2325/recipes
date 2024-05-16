package com.distribuidas.recetas.service.implementaciones;

import com.distribuidas.recetas.model.entities.Multimedia;
import com.distribuidas.recetas.repository.MultimediaRepository;
import com.distribuidas.recetas.service.interfaces.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MultimediaServiceImpl implements MultimediaService {

    @Autowired
    MultimediaRepository multimediaRepository;

    @Override
    public List<Multimedia> cargarMultimediasPaso(Collection<Multimedia> multimediaByIdPaso) {
        return this.multimediaRepository.saveAll(multimediaByIdPaso);
    }
}
