package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "ingredientsUsed")
public class IngredientUsed {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingredientsUsedID")
    private Integer IngredientsUsedID;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @Basic
    @Column(name = "ingredientID", insertable = false, updatable = false)
    private Integer ingredientID;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "unitID", insertable = false, updatable = false)
    private Integer unitID;
    @Basic
    @Column(name = "comments")
    private String comments;
    @ManyToOne
    @JoinColumn(name = "recipeID", referencedColumnName = "recipeID")
    @JsonBackReference(value = "receta-utilizados")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recipeByRecipeID;
    @ManyToOne
    @JoinColumn(name = "ingredientID", referencedColumnName = "ingredientID")
    private Ingredient ingredientsByIngredientID;
    @ManyToOne
    @JoinColumn(name = "unitID", referencedColumnName = "unitID")
    private UnitOfMeasurement unitsOfMeasurementByUnitID;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientUsed ingredientUsed = (IngredientUsed) o;

        if (!Objects.equals(IngredientsUsedID, ingredientUsed.IngredientsUsedID))
            return false;
        if (!Objects.equals(recipeID, ingredientUsed.recipeID)) return false;
        if (!Objects.equals(ingredientID, ingredientUsed.ingredientID))
            return false;
        if (!Objects.equals(quantity, ingredientUsed.quantity)) return false;
        if (!Objects.equals(unitID, ingredientUsed.unitID)) return false;
        return Objects.equals(comments, ingredientUsed.comments);
    }

    @Override
    public int hashCode() {
        int result = IngredientsUsedID != null ? IngredientsUsedID.hashCode() : 0;
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        result = 31 * result + (ingredientID != null ? ingredientID.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (unitID != null ? unitID.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
