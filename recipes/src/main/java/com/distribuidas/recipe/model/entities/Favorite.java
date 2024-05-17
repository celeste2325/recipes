package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "favorites")
public class Favorite {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "userID", insertable = false, updatable = false)
    private Integer userID;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User usersByUserID;
    @ManyToOne
    @JoinColumn(name = "recipeID", referencedColumnName = "recipeID", nullable = false)
    private Recipe recipeByRecipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorito = (Favorite) o;

        if (!Objects.equals(id, favorito.id)) return false;
        if (!Objects.equals(userID, favorito.userID)) return false;
        return Objects.equals(recipeID, favorito.recipeID);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "recetasByIdReceta=" + recipeByRecipeID +
                '}';
    }
}
