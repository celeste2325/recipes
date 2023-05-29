package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Paso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PasoService {
    List<Paso> buscaTodosLosPasosDeUnaReceta(Integer idReceta);
}
