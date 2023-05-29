package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Unidad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnidadService {

    List<Unidad> devolverUnidades();
}
