package com.distribuidas.recetas.modelo.dto.response;

import com.distribuidas.recetas.modelo.Foto;
import com.distribuidas.recetas.modelo.Paso;
import com.distribuidas.recetas.modelo.Tipo;
import com.distribuidas.recetas.modelo.Utilizado;
import lombok.Data;

import java.util.Collection;

@Data
public class RecetaResponseDto {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private Integer cantidadPersonas;
    private Collection<Foto> fotosByIdReceta;
    private Collection<Paso> pasosByIdReceta;
    private Integer idUsuario;
    private String nombreUsuario;
    private Tipo tiposByIdTipo;
    private Collection<Utilizado> utilizadosByIdReceta;
}
