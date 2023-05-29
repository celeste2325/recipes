package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import com.distribuidas.recetas.repositorios.MultimediaRepository;
import com.distribuidas.recetas.servicios.interfaces.MultimediaService;
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
