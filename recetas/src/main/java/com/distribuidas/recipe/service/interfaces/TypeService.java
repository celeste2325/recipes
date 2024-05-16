package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {

    List<Type> devolverTiposPlatos();

    List<Type> devolverPlatosPorBusquedaParcialTipoDePlato(String nombreParcialTipoPlato);
}
