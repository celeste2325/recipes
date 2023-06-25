package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.dto.ReemplazarRecetaResponseDto;
import com.distribuidas.recetas.modelo.entities.FechaReceta;
import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.repositorios.CalificacionRepository;
import com.distribuidas.recetas.repositorios.RecetaRepository;
import com.distribuidas.recetas.servicios.interfaces.RecetaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {
    @Autowired
    private CalificacionRepository calificacionRepository;
    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public Receta altaReceta(Receta newReceta) throws YaExisteUnaRecetaConMismoNombreYUsuarioException {
        if (recetaExistentePorUsuario(newReceta.getNombre(), newReceta.getUsuariosByIdUsuario().getIdUsuario()) == null) {
            FechaReceta fechaReceta = new FechaReceta();
            fechaReceta.setFechaCreacion(LocalDateTime.now());
            newReceta.setFechaRecetaByIdReceta(fechaReceta);
            fechaReceta.setRecetaByIdReceta(newReceta);
            return this.recetaRepository.save(newReceta);
        }
        throw new YaExisteUnaRecetaConMismoNombreYUsuarioException("Estimado/a, ya creo una receta con mismo nombre");
    }

    @Override
    public Receta updateReceta(Integer id, Receta newReceta) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> recetaAModificar = this.recetaRepository.findById(id);
        if (recetaAModificar.isPresent()) {
            //se eliminan los comentarios de la receta
            if (!recetaAModificar.get().getCalificacionesByIdReceta().isEmpty()) {
                this.eliminarComentarios(recetaAModificar.get().getIdReceta());
            }

            //edita los campos
            recetaAModificar.get().setNombre(newReceta.getNombre());
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

    @Override
    public void eliminarReceta(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> receta = this.recetaRepository.findById(id);
        if (receta.isPresent()) {
            this.recetaRepository.delete(receta.get());
        } else {
            throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta asociada al id ingresado");
        }
    }
    //TODO ESTA OK
    @Override
    public List<Receta> devolverRecetas(Integer idUsuario) {
        return this.recetaRepository.devolverTodasLasrecetasHabilitadas(idUsuario);
    }

    @Override
    public Receta recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreAndIdUsuario(nombreReceta, idUsuario);
    }

    //TODO esta ok
    @Override
    public List<Receta> recetasPorNombreOrdenNombreUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreOrderByNombreUser(nombreReceta, idUsuario);
    }
    //TODO esta ok
    @Override
    public List<Receta> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreOrderByAntiguedad(nombreReceta, idUsuario);
    }
    //TODO esta ok
    @Override
    public List<Receta> busquedaRecetasByParam(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio) {
        return this.recetaRepository.recetasByParamQuery(nombreReceta,idTipo,idIngrediente,idUsuario,idUsuarioObligatorio);
    }

    @Transactional
    @Override
    public ReemplazarRecetaResponseDto reemplazarReceta(Integer idReceta) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> recetaEncotrada = this.recetaRepository.findById(idReceta);
        ReemplazarRecetaResponseDto reemplazarRecetaResponseDto = new ReemplazarRecetaResponseDto();
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
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta asociada al id ingresado");
    }

    private void eliminarComentarios(Integer idReceta) {
        this.calificacionRepository.eliminarComentariosDeReceta(idReceta);
    }

    //TODO esta ok
    @Override
    public Receta devolverRecetaPorId(Integer id, Integer idUsuario) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<Receta> receta = this.recetaRepository.devuelveRecetasPorId(id, idUsuario);
        if (receta.isPresent()) {
            return receta.get();
        }
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta para el id ingresado");
    }

    //TODO ESTA OK
    @Override
    public List<Receta> devolverRecetasSinIngredienteOrdenadaPorAntiguedad(Integer idIngrediente, Integer idUsuario) {
        return this.recetaRepository.recetasSinIngredientesOrdenadaPorAntiguedad(idIngrediente,idUsuario);
    }
    @Override
    public List<Receta> devolverRecetasSinIngredienteOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario) {
        return this.recetaRepository.recetasSinIngredientesOrdenadaPorNombre(idIngrediente,idUsuario);
    }
    //TODO ESTA OK
    @Override
    public List<Receta> devolverRecetasSinIngredienteOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario) {
        return this.recetaRepository.recetasSinIngredientesOrdenadaPorNombreUser(idIngrediente,idUsuario);
    }

    //TODO ESTA OK
    @Override
    public List<Receta> devolverRecetasPorBusquedaParcialNombre(String nombreReceta, Integer idUsuario) {
        if (!Objects.equals(nombreReceta, "")) {
            return recetaRepository.findByNombreLikeIgnoreCase(nombreReceta,idUsuario);
        }
        return null;
    }

    //TODO ESTA OK
    @Override
    public List<Receta> devuelve3RecetasInicioApp(Integer idUsuario) {
        return this.recetaRepository.recetasPorUsuarioOrdenadasPorFecha(idUsuario);
    }

}
