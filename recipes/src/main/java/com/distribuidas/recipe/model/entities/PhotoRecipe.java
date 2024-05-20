package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "photosRecipes")
public class PhotoRecipe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "photoID")
    private Integer photoID;
    /*@Basic
    @Column(name = "idReceta", insertable = false, updatable = false)
    private Integer idReceta;*/
    @Basic
    @Column(name = "photoUrl")
    private String photoUrl;
    @Basic
    @Column(name = "extension")
    private String extension;
    @ManyToOne
    @JoinColumn(name = "recipeID", referencedColumnName = "recipeID", nullable = false)
    @JsonBackReference(value = "receta-foto")
    private Recipe recipeByRecipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoRecipe photo = (PhotoRecipe) o;

        if (!Objects.equals(photoID, photo.photoID)) return false;
        //if (!Objects.equals(idReceta, foto.idReceta)) return false;
        if (!Objects.equals(photoUrl, photo.photoUrl)) return false;
        return Objects.equals(extension, photo.extension);
    }

    @Override
    public int hashCode() {
        int result = photoID != null ? photoID.hashCode() : 0;
        //result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
}
