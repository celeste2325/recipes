package com.distribuidas.recetas.service.implementaciones;

import com.distribuidas.recetas.model.entities.Unidad;
import com.distribuidas.recetas.repository.UnidadRepository;
import com.distribuidas.recetas.service.interfaces.UnidadService;
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
