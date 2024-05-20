package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "recipes")
public class Recipe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "recipeID")
    private Integer recipeID;
    @Basic
    @Column(name = "userID", insertable = false, updatable = false)
    private Integer userID;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "urlPhoto")
    private String urlPhoto;
    @Basic
    @Column(name = "servings")
    private Integer servings;
    @Basic
    @Column(name = "numberPeople")
    private Integer numberPeople;
    @Basic
    @Column(name = "categoryID", insertable = false, updatable = false)
    private Integer categoryID;

    @OneToMany(mappedBy = "recipeByRecipeID")
    private Collection<Review> reviewByRecipeID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "recipeByRecipeID")
    private Collection<Favorite> favoritesByRecipeID;

    @OneToMany(mappedBy = "recipeByRecipeID", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<PhotoRecipe> photosByRecipeID;

    @OneToMany(mappedBy = "recipeByRecipeID", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Step> stepsByRecipeID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @JsonBackReference(value = "receta-usuario")
    private User userByUserID;

    @ManyToOne
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @JsonBackReference(value = "receta-tipo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category categoryByCategoryID;

    @OneToMany(mappedBy = "recipeByRecipeID", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<IngredientUsed> ingredientsUsedByRecipeID;

    @OneToOne(mappedBy = "recipeByRecipeID", cascade = CascadeType.ALL)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date dateByRecipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (!Objects.equals(recipeID, recipe.recipeID)) return false;
        if (!Objects.equals(userID, recipe.userID)) return false;
        if (!Objects.equals(name, recipe.name)) return false;
        if (!Objects.equals(description, recipe.description)) return false;
        if (!Objects.equals(urlPhoto, recipe.urlPhoto)) return false;
        if (!Objects.equals(servings, recipe.servings)) return false;
        if (!Objects.equals(numberPeople, recipe.numberPeople))
            return false;
        return Objects.equals(categoryID, recipe.categoryID);
    }

    @Override
    public int hashCode() {
        int result = recipeID != null ? recipeID.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (urlPhoto != null ? urlPhoto.hashCode() : 0);
        result = 31 * result + (servings != null ? servings.hashCode() : 0);
        result = 31 * result + (numberPeople != null ? numberPeople.hashCode() : 0);
        result = 31 * result + (categoryID != null ? categoryID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "RecipeID=" + recipeID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", servings=" + servings +
                ", date" + dateByRecipeID +
                ", number people=" + numberPeople +
                ", url photo=" + urlPhoto +
                ", photosByRecipeID=" + photosByRecipeID +
                ", stepsByRecipeID=" + stepsByRecipeID +
                ", categoriesByCategoryID=" + categoryByCategoryID +
                ", ingredientUsedByRecipeID=" + ingredientsUsedByRecipeID +
                '}';
    }
}
