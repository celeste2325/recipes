package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.exception.ExistingIngredientException;
import com.distribuidas.recipe.model.entities.Ingredient;
import com.distribuidas.recipe.repository.IngredientRepository;
import com.distribuidas.recipe.service.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredienteRepository;

    public Ingredient salvarIngrediente(Ingredient newIngrediente) throws ExistingIngredientException {
        Ingredient ingredienteEncontrado = this.ingredienteRepository.findByNombre(newIngrediente.getNombre());
        if (ingredienteEncontrado == null) {
            return this.ingredienteRepository.save(newIngrediente);
        } else {
            throw new ExistingIngredientException("El ingrediente que intenta crear ya existe");
        }

    }

    public List<Ingredient> listarIngredientes() {
        return this.ingredienteRepository.findAll();
    }

    @Override
    public List<Ingredient> devolverIngredientesPorBusquedaParcial(String nombreParcialIngrediente) {
        if (!Objects.equals(nombreParcialIngrediente, "")) {
            return this.ingredienteRepository.findByNombreLikeIgnoreCase(nombreParcialIngrediente + "%");
        }
        return null;
    }
}
