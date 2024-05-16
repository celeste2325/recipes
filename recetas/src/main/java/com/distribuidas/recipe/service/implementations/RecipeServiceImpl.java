package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.exception.RecipeDoesNotExistException;
import com.distribuidas.recipe.exception.ExistingRecipeException;
import com.distribuidas.recipe.model.dto.ReplaceRecipeResponseDto;
import com.distribuidas.recipe.model.entities.*;
import com.distribuidas.recipe.repository.*;
import com.distribuidas.recipe.service.interfaces.RecipeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private CalificationRepository calificacionRepository;
    @Autowired
    private RecipeRepository recetaRepository;
    @Autowired
    AuthorizationStatusRepository EstadoAutorizacionRepository;

    @Override
    public Recipe altaReceta(Recipe newReceta) throws ExistingRecipeException {
        if (recetaExistentePorUsuario(newReceta.getNombre(), newReceta.getUsuariosByIdUsuario().getIdUsuario()) == null) {
            DateOfRecipe fechaReceta = new DateOfRecipe();
            fechaReceta.setFechaCreacion(LocalDateTime.now());
            newReceta.setFechaRecetaByIdReceta(fechaReceta);
            fechaReceta.setRecetaByIdReceta(newReceta);


            Recipe receta = this.recetaRepository.save(newReceta);

            AuthorizationStatus estadoAutorizacion = new AuthorizationStatus();
            estadoAutorizacion.setTipoEstado("No autorizado");
            estadoAutorizacion.setIdEntidad(receta.getIdReceta());
            estadoAutorizacion.setTipoEntidad("Receta");
            this.EstadoAutorizacionRepository.save(estadoAutorizacion);
            return receta;
        }
        throw new ExistingRecipeException("Estimado/a, ya creo una receta con mismo nombre");
    }

    @Override
    public Recipe updateReceta(Integer id, Recipe newReceta) throws RecipeDoesNotExistException {
        Optional<Recipe> recetaAModificar = this.recetaRepository.findById(id);
        if (recetaAModificar.isPresent()) {
            //se eliminan los comentarios de la receta
            if (!recetaAModificar.get().getCalificacionesByIdReceta().isEmpty()) {
                this.eliminarComentarios(recetaAModificar.get().getIdReceta());
            }

            //edita los campos
            recetaAModificar.get().setNombre(newReceta.getNombre());
            recetaAModificar.get().setDescripcion(newReceta.getDescripcion());
            recetaAModificar.get().setFoto(newReceta.getFoto());
            recetaAModificar.get().setFotosByIdReceta(newReceta.getFotosByIdReceta());
            recetaAModificar.get().setPorciones(newReceta.getPorciones());
            recetaAModificar.get().setCantidadPersonas(newReceta.getCantidadPersonas());
            recetaAModificar.get().setTiposByIdTipo(newReceta.getTiposByIdTipo());
            recetaAModificar.get().setPasosByIdReceta(newReceta.getPasosByIdReceta());
            recetaAModificar.get().setUtilizadosByIdReceta(newReceta.getUtilizadosByIdReceta());


            recetaAModificar.get().getUtilizadosByIdReceta().forEach(utilizado -> {
                utilizado.setRecetasByIdReceta(recetaAModificar.get());
                var unidad =  new UnitOfMeasurement();
                unidad.setIdUnidad(utilizado.getIdUnidad());
                utilizado.setUnidadesByIdUnidad( unidad);

                var ingrediente = new Ingredient();
                ingrediente.setIdIngrediente(utilizado.getIdIngrediente());
                utilizado.setIngredientesByIdIngrediente(ingrediente);
            });

            recetaAModificar.get().getPasosByIdReceta().forEach(paso -> {
                paso.setRecetasByIdReceta(recetaAModificar.get());
            });



            return this.recetaRepository.save(recetaAModificar.get());
        }
        throw new RecipeDoesNotExistException("No existe una receta asociada al id ingresado");
    }

    @Override
    public void eliminarReceta(Integer id) throws RecipeDoesNotExistException {
        Optional<Recipe> receta = this.recetaRepository.findById(id);
        if (receta.isPresent()) {
            this.recetaRepository.delete(receta.get());
        } else {
            throw new RecipeDoesNotExistException("No existe una receta asociada al id ingresado");
        }
    }
    //TODO ESTA OK
    @Override
    public List<Recipe> devolverRecetas(Integer idUsuario) {
        return this.recetaRepository.devolverTodasLasrecetasHabilitadas(idUsuario);
    }

    @Override
    public Recipe recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreAndIdUsuario(nombreReceta, idUsuario);
    }

    //TODO esta ok
    @Override
    public List<Recipe> recetasPorNombreOrdenNombreUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreOrderByNombreUser(nombreReceta, idUsuario);
    }
    //TODO esta ok
    @Override
    public List<Recipe> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreOrderByAntiguedad(nombreReceta, idUsuario);
    }
    //TODO esta ok
    @Override
    public List<Recipe> busquedaRecetasByParam(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio) {
        return this.recetaRepository.recetasByParamQuery(nombreReceta,idTipo,idIngrediente,idUsuario,idUsuarioObligatorio);
    }

    @Transactional
    @Override
    public ReplaceRecipeResponseDto reemplazarReceta(Integer idReceta) throws RecipeDoesNotExistException {
        Optional<Recipe> recetaEncotrada = this.recetaRepository.findById(idReceta);
        ReplaceRecipeResponseDto reemplazarRecetaResponseDto = new ReplaceRecipeResponseDto();
        if (recetaEncotrada.isPresent()) {
            reemplazarRecetaResponseDto.setNombre(recetaEncotrada.get().getNombre());
            //se eliminan los comentarios de la receta pero se mantienen las calificaciones
            if (!recetaEncotrada.get().getCalificacionesByIdReceta().isEmpty()) {
                this.eliminarComentarios(recetaEncotrada.get().getIdReceta());
                reemplazarRecetaResponseDto.setCalificacionesByIdReceta(recetaEncotrada.get().getCalificacionesByIdReceta());
            }
            //implementar eliminacion logica
            //TODO eliminar cuando la receta este creada
            //this.eliminarReceta(recetaEncotrada.get().getIdReceta());
            return reemplazarRecetaResponseDto;
        }
        throw new RecipeDoesNotExistException("No existe una receta asociada al id ingresado");
    }

    private void eliminarComentarios(Integer idReceta) {
        this.calificacionRepository.eliminarComentariosDeReceta(idReceta);
    }

    //TODO esta ok
    @Override
    public Recipe devolverRecetaPorId(Integer id, Integer idUsuario) throws RecipeDoesNotExistException {
        Optional<Recipe> receta = this.recetaRepository.devuelveRecetasPorId(id, idUsuario);
        if (receta.isPresent()) {
            return receta.get();
        }
        throw new RecipeDoesNotExistException("No existe una receta para el id ingresado");
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> devolverRecetasSinIngredienteOrdenadaPorAntiguedad(Integer idIngrediente, Integer idUsuario) {
        return this.recetaRepository.recetasSinIngredientesOrdenadaPorAntiguedad(idIngrediente,idUsuario);
    }
    @Override
    public List<Recipe> devolverRecetasSinIngredienteOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario) {
        return this.recetaRepository.recetasSinIngredientesOrdenadaPorNombre(idIngrediente,idUsuario);
    }
    //TODO ESTA OK
    @Override
    public List<Recipe> devolverRecetasSinIngredienteOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario) {
        return this.recetaRepository.recetasSinIngredientesOrdenadaPorNombreUser(idIngrediente,idUsuario);
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> devolverRecetasPorBusquedaParcialNombre(String nombreReceta, Integer idUsuario) {
        if (!Objects.equals(nombreReceta, "")) {
            return recetaRepository.findByNombreLikeIgnoreCase(nombreReceta,idUsuario);
        }
        return null;
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> devuelve3RecetasInicioApp(Integer idUsuario) {
        return this.recetaRepository.recetasPorUsuarioOrdenadasPorFecha(idUsuario);
    }

}
