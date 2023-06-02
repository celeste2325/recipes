package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Tipo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoService {

    List<Tipo> devolverTiposPlatos();

    List<Tipo> devolverPlatosPorBusquedaParcialTipoDePlato(String nombreParcialTipoPlato);
}
