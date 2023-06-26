package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Calificacion;
import com.distribuidas.recetas.repositorios.CalificacionRepository;
import com.distribuidas.recetas.servicios.interfaces.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion calificarReceta(Calificacion newCalificacion) {
        return this.calificacionRepository.save(newCalificacion);
    }

    @Override
    public List<Calificacion> devolverCalificacionesByIdReceta(Integer idReceta) {
        return this.calificacionRepository.findByIdReceta(idReceta);
    }

    @Override
    public void eliminarCalificacionDeReceta(Integer calificacionId) {
        this.calificacionRepository.deleteById(calificacionId);
    }
}
