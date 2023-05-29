package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Multimedia;
import com.distribuidas.recetas.modelo.entities.Paso;
import com.distribuidas.recetas.repositorios.PasoRepository;
import com.distribuidas.recetas.servicios.interfaces.PasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PasoServiceImpl implements PasoService {
    @Autowired
    private PasoRepository pasoRepository;

    @Override
    public Paso cargarMultimediasPaso(Collection<Multimedia> multimediaByIdPaso) {
        return null;
    }

    @Override
    public List<Paso> buscaTodosLosPasosDeUnaReceta(Integer idReceta) {
        return this.pasoRepository.findByIdReceta(idReceta);
    }
}
