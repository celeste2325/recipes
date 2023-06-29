package com.distribuidas.recetas.modelo.dto.response;

import com.distribuidas.recetas.modelo.entities.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RecetaResponseDto {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private FechaReceta fechaRecetaByIdReceta;
    private Integer cantidadPersonas;
    private Usuario usuariosByIdUsuario;
    private Tipo tiposByIdTipo;
    private String foto;
    private Collection<Foto> fotosByIdReceta;
    private List<Paso> pasosByIdReceta;
    private List<Utilizado> utilizadosByIdReceta;
}
