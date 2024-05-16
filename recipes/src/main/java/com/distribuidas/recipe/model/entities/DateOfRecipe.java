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
@Table(name = "fechasReceta")
public class DateOfRecipe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "idReceta", insertable = false, updatable = false)
    private Integer idReceta;
    @Basic
    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "idReceta", referencedColumnName = "idReceta")
    @JsonBackReference(value = "receta-fechaReceta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recetaByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateOfRecipe that = (DateOfRecipe) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(idReceta, that.idReceta)) return false;
        return Objects.equals(fechaCreacion, that.fechaCreacion);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        return result;
    }
}
