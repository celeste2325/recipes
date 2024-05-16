package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.model.entities.Tipo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoService {

    List<Tipo> devolverTiposPlatos();

    List<Tipo> devolverPlatosPorBusquedaParcialTipoDePlato(String nombreParcialTipoPlato);
}
