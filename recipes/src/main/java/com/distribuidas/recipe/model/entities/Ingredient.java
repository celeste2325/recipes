package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "ingredients")
public class Ingredient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingredientID")
    private Integer ingredientID;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "ingredientsByIngredientID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<IngredientUsed> IngredientUsedByIngredientID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (!Objects.equals(ingredientID, that.ingredientID))
            return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = ingredientID != null ? ingredientID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
