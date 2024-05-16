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
@Table(name = "tipos")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTipo")
    private Integer idTipo;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tiposByIdTipo")
    @JsonManagedReference(value = "receta-tipo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Recipe> recetasByIdTipo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category tipo = (Category) o;

        if (!Objects.equals(idTipo, tipo.idTipo)) return false;
        return Objects.equals(descripcion, tipo.descripcion);
    }

    @Override
    public int hashCode() {
        int result = idTipo != null ? idTipo.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
