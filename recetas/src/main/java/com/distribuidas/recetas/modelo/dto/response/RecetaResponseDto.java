package com.distribuidas.recetas.modelo.dto.response;

import com.distribuidas.recetas.modelo.Foto;
import com.distribuidas.recetas.modelo.Paso;
import com.distribuidas.recetas.modelo.Tipo;
import com.distribuidas.recetas.modelo.Utilizado;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RecetaResponseDto {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private Integer cantidadPersonas;
    private Integer idUsuario;
    private String nickname;
    private Tipo tiposByIdTipo;
    private Collection<Foto> fotosByIdReceta;
    private List<Paso> pasosByIdReceta;
    private List<Utilizado> utilizadosByIdReceta;
}
