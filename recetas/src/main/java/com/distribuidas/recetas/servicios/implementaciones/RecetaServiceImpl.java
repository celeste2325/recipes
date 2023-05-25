package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.Receta;
import com.distribuidas.recetas.repositorios.RecetaRepository;
import com.distribuidas.recetas.servicios.interfaces.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public Receta altaReceta(Receta newReceta) throws YaExisteUnaRecetaConMismoNombreYUsuarioException {
        if (recetaExistentePorUsuario(newReceta.getNombre(), newReceta.getUsuariosByIdUsuario().getIdUsuario()) == null) {
            return this.recetaRepository.save(newReceta);
        }
        throw new YaExisteUnaRecetaConMismoNombreYUsuarioException("Estimado/a, ya creo una receta con mismo nombre");
    }

    @Override
    public Receta updateReceta(Integer id, Receta newReceta) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> recetaAModificar = this.recetaRepository.findById(id);
        if (recetaAModificar.isPresent()) {
            //TODO ELIMINAR COMENTARIOS
            //edita los demas campos
            //TODO se puede cambiar el nombre?
            recetaAModificar.get().setDescripcion(newReceta.getDescripcion());
            recetaAModificar.get().setFotosByIdReceta(newReceta.getFotosByIdReceta());
            recetaAModificar.get().setPorciones(newReceta.getPorciones());
            recetaAModificar.get().setCantidadPersonas(newReceta.getCantidadPersonas());
            recetaAModificar.get().setTiposByIdTipo(newReceta.getTiposByIdTipo());
            recetaAModificar.get().setPasosByIdReceta(newReceta.getPasosByIdReceta());
            recetaAModificar.get().setUtilizadosByIdReceta(newReceta.getUtilizadosByIdReceta());

            return this.recetaRepository.save(recetaAModificar.get());
        }
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta asociada al id ingresado");
    }

    //TODO esperar respuesta si la eliminacion es logica o fisica
    @Override
    public void eliminarReceta(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> receta = this.recetaRepository.findById(id);
        receta.ifPresent(value -> this.recetaRepository.delete(value));
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta asociada al id ingresado");
    }

    @Override
    public List<Receta> devolverRecetas() {
        return this.recetaRepository.findAll();
    }

    @Override
    public Receta recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreAndIdUsuario(nombreReceta, idUsuario);
    }

    @Override
    public List<Receta> devolverRecetasPorParamQueries(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario) {
        if (!Objects.equals(nombreReceta, "")) {
            return this.recetaRepository.findByNombre(nombreReceta).stream().sorted().collect(Collectors.toList());
        } else if (!idTipo.equals(0)) {
            return this.recetaRepository.findByIdTipo(idTipo);
        } else if (!idUsuario.equals(0)) {
            return this.recetaRepository.findByIdUsuario(idUsuario);
        } else if (!idIngrediente.equals(0)) {
            return this.recetaRepository.recetasPorIngrediente(idUsuario);
        }
        return null;
    }

    @Override
    public Receta devolverRecetaPorId(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> receta = this.recetaRepository.findById(id);
        if (receta.isPresent()) {
            return receta.get();
        }
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta para el id ingresado");
    }

    @Override
    public List<Receta> devolverRecetasSinIngrediente(Integer idIngrediente) {
        return this.recetaRepository.recetasSinIngredientes(idIngrediente);
    }
}
