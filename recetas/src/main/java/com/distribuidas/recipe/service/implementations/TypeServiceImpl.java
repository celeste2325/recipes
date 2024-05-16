package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Type;
import com.distribuidas.recipe.repository.TypeRepository;
import com.distribuidas.recipe.service.interfaces.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository tipoRepository;

    @Override
    public List<Type> devolverTiposPlatos() {
        return this.tipoRepository.findAll();
    }

    @Override
    public List<Type> devolverPlatosPorBusquedaParcialTipoDePlato(String nombreParcialTipoPlato) {
        if (!Objects.equals(nombreParcialTipoPlato, "")) {
            return this.tipoRepository.findByDescripcionLikeIgnoreCase(nombreParcialTipoPlato + "%");
        }
        return null;
    }
}
