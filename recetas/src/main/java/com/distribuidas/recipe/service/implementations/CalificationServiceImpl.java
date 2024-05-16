package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Rating;
import com.distribuidas.recipe.repository.CalificationRepository;
import com.distribuidas.recipe.service.interfaces.CalificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificationServiceImpl implements CalificationService {
    @Autowired
    private CalificationRepository calificacionRepository;

    @Override
    public Rating calificarReceta(Rating newCalificacion) {
        return this.calificacionRepository.save(newCalificacion);
    }

    @Override
    public List<Rating> devolverCalificacionesByIdReceta(Integer idReceta) {
        return this.calificacionRepository.findByIdReceta(idReceta);
    }

    @Override
    public void eliminarCalificacionDeReceta(Integer calificacionId) {
        this.calificacionRepository.deleteById(calificacionId);
    }
}
