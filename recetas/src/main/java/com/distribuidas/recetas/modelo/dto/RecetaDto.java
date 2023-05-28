package com.distribuidas.recetas.modelo.dto;

import com.distribuidas.recetas.modelo.*;
import lombok.Data;

import java.util.Collection;

@Data
public class RecetaDto {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private Integer cantidadPersonas;
    private Collection<Calificacion> calificacionesByIdReceta;
    private Collection<Favorito> favoritosByIdReceta;
    private Collection<Foto> fotosByIdReceta;
    private Collection<Paso> pasosByIdReceta;
    private Usuario usuariosByIdUsuario;
    private Tipo tiposByIdTipo;
    private Collection<Utilizado> utilizadosByIdReceta;
}
