package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.exception.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.exception.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.model.dto.ReemplazarRecetaResponseDto;
import com.distribuidas.recetas.model.entities.Receta;

import java.util.List;

public interface RecetaService {
    Receta altaReceta(Receta newReceta) throws YaExisteUnaRecetaConMismoNombreYUsuarioException;

    Receta updateReceta(Integer id, Receta newReceta) throws NoExisteUnaRecetaParaElIdIngresadoException;

    void eliminarReceta(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException;

    List<Receta> devolverRecetas(Integer idUsuario);

    Receta recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) throws YaExisteUnaRecetaConMismoNombreYUsuarioException;

    Receta devolverRecetaPorId(Integer id, Integer idUsuario) throws NoExisteUnaRecetaParaElIdIngresadoException;
    List<Receta> devolverRecetasSinIngredienteOrdenadaPorAntiguedad(Integer idIngrediente, Integer idUsuario);
    List<Receta> devolverRecetasSinIngredienteOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario);

    List<Receta> devolverRecetasPorBusquedaParcialNombre(String nombreReceta, Integer idUsuario);

    List<Receta> devuelve3RecetasInicioApp(Integer idUsuario);

    List<Receta> recetasPorNombreOrdenNombreUsuario(String nombreReceta, Integer idUsuario);

    List<Receta> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta, Integer idUsuario);

    List<Receta> busquedaRecetasByParam(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio);
    ReemplazarRecetaResponseDto reemplazarReceta(Integer idReceta) throws NoExisteUnaRecetaParaElIdIngresadoException;

    List<Receta> devolverRecetasSinIngredienteOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario);
}
