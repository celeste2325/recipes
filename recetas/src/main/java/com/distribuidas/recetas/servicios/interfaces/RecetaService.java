package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.dto.response.ReemplazarRecetaResponseDto;
import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.modelo.entities.RecetaAutorizada;

import java.util.List;

public interface RecetaService {
    Receta altaReceta(Receta newReceta) throws YaExisteUnaRecetaConMismoNombreYUsuarioException;

    Receta updateReceta(Integer id, Receta newReceta) throws NoExisteUnaRecetaParaElIdIngresadoException;

    void eliminarReceta(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException;

    List<RecetaAutorizada> devolverRecetas();

    RecetaAutorizada recetaExistentePorUsuario(String nombreReceta, Integer idUsuario);

    List<RecetaAutorizada> devolverRecetasPorParamQueries(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario);

    RecetaAutorizada devolverRecetaPorId(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException;

    List<RecetaAutorizada> devolverRecetasSinIngrediente(Integer idIngrediente);

    List<RecetaAutorizada> devolverRecetasPorBusquedaParcialNombre(String nombreReceta);

    List<RecetaAutorizada> devuelve3RecetasInicioApp(Integer idUsuario);

    List<RecetaAutorizada> recetasPorNombreOrdenNombreUsuario(String nombreReceta);

    List<RecetaAutorizada> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta);

    ReemplazarRecetaResponseDto reemplazarReceta(Integer idReceta) throws NoExisteUnaRecetaParaElIdIngresadoException;

}
