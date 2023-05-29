package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface MultimediaService {

    List<Multimedia> cargarMultimediasPaso(Collection<Multimedia> multimediaByIdPaso);
}
