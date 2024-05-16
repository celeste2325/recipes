package com.distribuidas.recetas.service.implementaciones;

import com.distribuidas.recetas.model.entities.Paso;
import com.distribuidas.recetas.repository.PasoRepository;
import com.distribuidas.recetas.service.interfaces.PasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasoServiceImpl implements PasoService {
    @Autowired
    private PasoRepository pasoRepository;

    @Override
    public List<Paso> buscaTodosLosPasosDeUnaReceta(Integer idReceta) {
        return this.pasoRepository.findByIdReceta(idReceta);
    }
}
