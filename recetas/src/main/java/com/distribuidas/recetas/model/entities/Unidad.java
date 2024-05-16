package com.distribuidas.recetas.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "unidades")
public class Unidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUnidad")
    private Integer idUnidad;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "unidadesByIdUnidadOrigen")
    private Collection<Conversion> conversionesByIdUnidad;
    @OneToMany(mappedBy = "unidadesByIdUnidadDestino")
    private Collection<Conversion> conversionesByIdUnidad_0;
    @OneToMany(mappedBy = "unidadesByIdUnidad")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Utilizado> utilizadosByIdUnidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unidad unidad = (Unidad) o;

        if (!Objects.equals(idUnidad, unidad.idUnidad)) return false;
        return Objects.equals(descripcion, unidad.descripcion);
    }

    @Override
    public int hashCode() {
        int result = idUnidad != null ? idUnidad.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
