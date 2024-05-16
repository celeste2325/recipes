package com.distribuidas.recipe.model.dto.response;

import com.distribuidas.recipe.model.entities.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RecetaResponseDto {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private DateOfRecipe fechaRecetaByIdReceta;
    private Integer cantidadPersonas;
    private User usuariosByIdUsuario;
    private Type tiposByIdTipo;
    private String foto;
    private Collection<Photo> fotosByIdReceta;
    private List<Step> pasosByIdReceta;
    private List<IngredientUsed> utilizadosByIdReceta;
}
