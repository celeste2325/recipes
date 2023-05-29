package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import com.distribuidas.recetas.modelo.entities.Paso;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface PasoService {

    Paso cargarMultimediasPaso(Collection<Multimedia> multimediaByIdPaso);

    List<Paso> buscaTodosLosPasosDeUnaReceta(Integer idReceta);
}
