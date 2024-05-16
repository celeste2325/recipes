package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.exception.ElIngredienteYaExisteException;
import com.distribuidas.recetas.model.entities.Ingrediente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredienteService {

    Ingrediente salvarIngrediente(Ingrediente newIngrediente) throws ElIngredienteYaExisteException;

    List<Ingrediente> listarIngredientes();

    List<Ingrediente> devolverIngredientesPorBusquedaParcial(String nombreParcialIngrediente);
}
