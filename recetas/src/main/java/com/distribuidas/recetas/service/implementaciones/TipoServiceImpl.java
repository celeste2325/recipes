package com.distribuidas.recetas.service.implementaciones;

import com.distribuidas.recetas.model.entities.Tipo;
import com.distribuidas.recetas.repository.TipoRepository;
import com.distribuidas.recetas.service.interfaces.TipoService;
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
