package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.model.entities.Paso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PasoService {
    List<Paso> buscaTodosLosPasosDeUnaReceta(Integer idReceta);
}
