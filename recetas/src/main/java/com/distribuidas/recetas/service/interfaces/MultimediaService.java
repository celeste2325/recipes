package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.model.entities.Multimedia;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface MultimediaService {

    List<Multimedia> cargarMultimediasPaso(Collection<Multimedia> multimediaByIdPaso);
}
