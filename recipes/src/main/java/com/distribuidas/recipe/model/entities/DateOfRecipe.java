package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "datesOfRecipe")
public class DateOfRecipe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @Basic
    @Column(name = "dateCreation")
    private LocalDateTime dateCreation;

    @OneToOne
    @JoinColumn(name = "recipeID", referencedColumnName = "recipeID")
    @JsonBackReference(value = "receta-fechaReceta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recipeByRecipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateOfRecipe that = (DateOfRecipe) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(recipeID, that.recipeID)) return false;
        return Objects.equals(dateCreation, that.dateCreation);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        return result;
    }
}
