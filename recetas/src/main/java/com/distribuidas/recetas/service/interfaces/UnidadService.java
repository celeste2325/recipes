package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.model.entities.Unidad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnidadService {

    List<Unidad> devolverUnidades();
}
