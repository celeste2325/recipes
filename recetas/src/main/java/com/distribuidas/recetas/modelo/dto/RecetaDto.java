package com.distribuidas.recetas.modelo.dto;

import com.distribuidas.recetas.modelo.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

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
    private List<Paso> pasosByIdReceta;
    private Usuario usuariosByIdUsuario;
    private Tipo tiposByIdTipo;
    private List<Utilizado> utilizadosByIdReceta;

}
