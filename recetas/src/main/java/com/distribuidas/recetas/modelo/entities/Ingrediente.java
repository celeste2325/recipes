package com.distribuidas.recetas.modelo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Setter
@Getter
@Table(name = "ingredientes")
public class Ingrediente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idIngrediente")
    private Integer idIngrediente;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "ingredientesByIdIngrediente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Utilizado> utilizadosByIdIngrediente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingrediente that = (Ingrediente) o;

        if (idIngrediente != null ? !idIngrediente.equals(that.idIngrediente) : that.idIngrediente != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idIngrediente != null ? idIngrediente.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
