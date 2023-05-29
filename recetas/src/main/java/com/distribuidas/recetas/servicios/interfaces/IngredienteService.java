package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.excepciones.ElIngredienteYaExisteException;
import com.distribuidas.recetas.modelo.entities.Ingrediente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredienteService {

    Ingrediente salvarIngrediente(Ingrediente newIngrediente) throws ElIngredienteYaExisteException;

    List<Ingrediente> listarIngredientes();
}
