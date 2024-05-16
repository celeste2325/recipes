package com.distribuidas.recipe.model.dto;

import com.distribuidas.recipe.model.entities.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RecipeDto {
    private Integer idReceta;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private String foto;
    private Integer cantidadPersonas;
    private Collection<Rating> calificacionesByIdReceta;
    private Collection<Favorite> favoritosByIdReceta;
    private Collection<Photo> fotosByIdReceta;
    private List<Step> pasosByIdReceta;
    private User usuariosByIdUsuario;
    private Category tiposByIdTipo;
    private List<IngredientUsed> utilizadosByIdReceta;

}
