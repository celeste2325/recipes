package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Unidad;
import com.distribuidas.recetas.repositorios.UnidadRepository;
import com.distribuidas.recetas.servicios.interfaces.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadServiceImpl implements UnidadService {
    @Autowired
    private UnidadRepository unidadRepository;

    @Override
    public List<Unidad> devolverUnidades() {
        return this.unidadRepository.findAll();
    }
}
