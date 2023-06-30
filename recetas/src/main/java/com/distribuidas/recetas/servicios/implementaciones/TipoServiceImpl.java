package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Tipo;
import com.distribuidas.recetas.repositorios.TipoRepository;
import com.distribuidas.recetas.servicios.interfaces.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TipoServiceImpl implements TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    @Override
    public List<Tipo> devolverTiposPlatos() {
        return this.tipoRepository.findAll();
    }

    @Override
    public List<Tipo> devolverPlatosPorBusquedaParcialTipoDePlato(String nombreParcialTipoPlato) {
        if (!Objects.equals(nombreParcialTipoPlato, "")) {
            return this.tipoRepository.findByDescripcionLikeIgnoreCase(nombreParcialTipoPlato + "%");
        }
        return null;
    }
}
