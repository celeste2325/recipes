package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.excepciones.NoExisteUnaRecetaParaElIdIngresadoException;
import com.distribuidas.recetas.excepciones.YaExisteUnaRecetaConMismoNombreYUsuarioException;
import com.distribuidas.recetas.modelo.entities.Receta;

import java.util.List;

public interface RecetaService {
    Receta altaReceta(Receta newReceta) throws YaExisteUnaRecetaConMismoNombreYUsuarioException;

    Receta updateReceta(Integer id, Receta newReceta) throws NoExisteUnaRecetaParaElIdIngresadoException;

    void eliminarReceta(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException;

    List<Receta> devolverRecetas();

    Receta recetaExistentePorUsuario(String nombreReceta, Integer idUsuario);

    List<Receta> devolverRecetasPorParamQueries(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario);

    Receta devolverRecetaPorId(Integer id) throws NoExisteUnaRecetaParaElIdIngresadoException;

    List<Receta> devolverRecetasSinIngrediente(Integer idIngrediente);
}
