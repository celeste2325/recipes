package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.entities.FechaReceta;
import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.repositorios.RecetaRepository;
import com.distribuidas.recetas.servicios.interfaces.RecetaService;
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
            //TODO ELIMINAR COMENTARIOS
            //edita los demas campos
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

    //TODO crear un reemplazar q llame a este metodo pero antes conserve las calificaciones para cargar a la nueva receta
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
    public List<Receta> devolverRecetas() {
        return this.recetaRepository.findAll();
    }

    @Override
    public Receta recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) {
        return this.recetaRepository.findByNombreAndIdUsuario(nombreReceta, idUsuario);
    }

    //TODO va ser modificador por el refactor
    // @RequestHeader("usuari_id")String usuariID  fecha <-poner donde haga falta
    @Override
    public List<Receta> recetasPorNombreOrdenNombreUsuario(String nombreReceta) {
        return this.recetaRepository.findByNombreOrderByNombreUser(nombreReceta);
    }

    @Override
    public List<Receta> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta) {
        return this.recetaRepository.findByNombreOrderByAntiguedad(nombreReceta);
    }


    //TODO va ser modificador por el refactor
    @Override
    public List<Receta> devolverRecetasPorParamQueries(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario) {
        if (!Objects.equals(nombreReceta, "")) {
            return this.recetaRepository.findByNombre(nombreReceta);
        } else if (!idTipo.equals(0)) {
            List<Receta> recetasPorTipoDePlato = this.recetaRepository.findByIdTipo(idTipo);
            recetasPorTipoDePlato.sort(Comparator.comparing((Receta receta) -> receta.getNombre().toLowerCase()));
            return recetasPorTipoDePlato;

        } else if (!idUsuario.equals(0)) {
            List<Receta> byIdUsuario = this.recetaRepository.findByIdUsuario(idUsuario);
            byIdUsuario.sort(Comparator.comparing((Receta receta) -> receta.getNombre().toLowerCase()));
            return byIdUsuario;

        } else if (!idIngrediente.equals(0)) {
            return this.recetaRepository.recetasPorIngrediente(idIngrediente);
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

    @Override
    public List<Receta> devolverRecetasPorBusquedaParcialNombre(String nombreReceta) {
        if (!Objects.equals(nombreReceta, "")) {
            return recetaRepository.findByNombreLikeIgnoreCase("%" + nombreReceta + "%");
        }
        return null;
    }

    @Override
    public List<Receta> devuelve3RecetasInicioApp(Integer idUsuario) {
        return this.recetaRepository.recetasPorUsuarioOrdenadasPorFecha(idUsuario);
    }

}
