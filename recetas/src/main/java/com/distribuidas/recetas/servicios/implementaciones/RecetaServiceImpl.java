package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.dto.response.ReemplazarRecetaResponseDto;
import com.distribuidas.recetas.modelo.entities.FechaReceta;
import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.modelo.entities.RecetaAutorizada;
import com.distribuidas.recetas.repositorios.CalificacionRepository;
import com.distribuidas.recetas.repositorios.RecetaAutorizadaRepository;
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

    @Autowired
    private RecetaAutorizadaRepository recetaAutorizadaRepository;

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

    @Transactional
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

    @Override
    public List<RecetaAutorizada> devolverRecetas() {
        return this.recetaAutorizadaRepository.findAll();
    }

    @Override
    public RecetaAutorizada recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaAutorizadaRepository.findByNombreAndIdUsuario(nombreReceta, idUsuario);
    }

    @Override
    public List<RecetaAutorizada> recetasPorNombreOrdenNombreUsuario(String nombreReceta) {
        return this.recetaRepository.findByNombreOrderByNombreUser(nombreReceta);
    }

    @Override
    public List<RecetaAutorizada> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta) {
        return this.recetaRepository.findByNombreOrderByAntiguedad(nombreReceta);
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
            //this.eliminarReceta(recetaEncotrada.get().getIdReceta());
            return reemplazarRecetaResponseDto;
        }
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta asociada al id ingresado");
    }

    private void eliminarComentarios(Integer idReceta) {
        this.calificacionRepository.eliminarComentariosDeReceta(idReceta);
    }

    @Override
    public List<RecetaAutorizada> devolverRecetasPorParamQueries(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario) {
        if (!Objects.equals(nombreReceta, "")) {
            return this.recetaAutorizadaRepository.findByNombre(nombreReceta);
        } else if (!idTipo.equals(0)) {
            List<RecetaAutorizada> recetasPorTipoDePlato = this.recetaAutorizadaRepository.findByIdTipo(idTipo);
            recetasPorTipoDePlato.sort(Comparator.comparing((RecetaAutorizada receta) -> receta.getNombre().toLowerCase()));
            return recetasPorTipoDePlato;

        } else if (!idUsuario.equals(0)) {
            List<RecetaAutorizada> byIdUsuario = this.recetaAutorizadaRepository.findByIdUsuario(idUsuario);
            byIdUsuario.sort(Comparator.comparing((RecetaAutorizada receta) -> receta.getNombre().toLowerCase()));
            return byIdUsuario;

        } else if (!idIngrediente.equals(0)) {
            return this.recetaRepository.recetasPorIngrediente(idIngrediente);
        }
        return null;
    }

    @Override
    public RecetaAutorizada devolverRecetaPorId(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException {
        Optional<RecetaAutorizada> receta = this.recetaAutorizadaRepository.findById(id);
        if (receta.isPresent()) {
            return receta.get();
        }
        throw new NoExisteUnaRecetaParaElIdIngresadoException("No existe una receta para el id ingresado");
    }

    @Override
    public List<RecetaAutorizada> devolverRecetasSinIngrediente(Integer idIngrediente) {
        return this.recetaRepository.recetasSinIngredientes(idIngrediente);
    }

    @Override
    public List<RecetaAutorizada> devolverRecetasPorBusquedaParcialNombre(String nombreReceta) {
        if (!Objects.equals(nombreReceta, "")) {
            return recetaAutorizadaRepository.findByNombreLikeIgnoreCase("%" + nombreReceta + "%");
        }
        return null;
    }

    @Override
    public List<RecetaAutorizada> devuelve3RecetasInicioApp(Integer idUsuario) {
        return this.recetaRepository.recetasPorUsuarioOrdenadasPorFecha(idUsuario);
    }

}
