package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Setter
@Getter
@Table(name = "tipos", schema = "dbo", catalog = "recetas")
public class Tipo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTipo")
    private Integer idTipo;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tiposByIdTipo")
    private Collection<Receta> recetasByIdTipo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipo tipo = (Tipo) o;

        if (idTipo != null ? !idTipo.equals(tipo.idTipo) : tipo.idTipo != null) return false;
        if (descripcion != null ? !descripcion.equals(tipo.descripcion) : tipo.descripcion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipo != null ? idTipo.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
