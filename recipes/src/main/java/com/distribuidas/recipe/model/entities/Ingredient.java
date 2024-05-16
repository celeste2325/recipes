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
@Table(name = "ingredientes")
public class Ingredient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idIngrediente")
    private Integer idIngrediente;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "ingredientesByIdIngrediente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<IngredientUsed> utilizadosByIdIngrediente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (!Objects.equals(idIngrediente, that.idIngrediente))
            return false;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        int result = idIngrediente != null ? idIngrediente.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
