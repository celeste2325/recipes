package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.excepciones.ElIngredienteYaExisteException;
import com.distribuidas.recetas.modelo.entities.Ingrediente;
import com.distribuidas.recetas.repositorios.IngredienteRepository;
import com.distribuidas.recetas.servicios.interfaces.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Ingrediente salvarIngrediente(Ingrediente newIngrediente) throws ElIngredienteYaExisteException {
        Ingrediente ingredienteEncontrado = this.ingredienteRepository.findByNombre(newIngrediente.getNombre());
        if (ingredienteEncontrado == null) {
            return this.ingredienteRepository.save(newIngrediente);
        } else {
            throw new ElIngredienteYaExisteException("El ingrediente que intenta crear ya existe");
        }

    }

    public List<Ingrediente> listarIngredientes() {
        return this.ingredienteRepository.findAll();
    }

    @Override
    public List<Ingrediente> devolverIngredientesPorBusquedaParcial(String nombreParcialIngrediente) {
        if (!Objects.equals(nombreParcialIngrediente, "")) {
            return this.ingredienteRepository.findByNombreLikeIgnoreCase(nombreParcialIngrediente + "%");
        }
        return null;
    }
}
