package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "categories")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "categoryID")
    private Integer categoryID;
    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "categoryByCategoryID")
    @JsonManagedReference(value = "receta-tipo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Recipe> recipeByCategoryID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category tipo = (Category) o;

        if (!Objects.equals(categoryID, tipo.categoryID)) return false;
        return Objects.equals(description, tipo.description);
    }

    @Override
    public int hashCode() {
        int result = categoryID != null ? categoryID.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
