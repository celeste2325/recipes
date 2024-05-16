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
@Table(name = "utilizados")
public class IngredientUsed {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUtilizado")
    private Integer idUtilizado;
    @Basic
    @Column(name = "idReceta", insertable = false, updatable = false)
    private Integer idReceta;
    @Basic
    @Column(name = "idIngrediente", insertable = false, updatable = false)
    private Integer idIngrediente;
    @Basic
    @Column(name = "cantidad")
    private Integer cantidad;
    @Basic
    @Column(name = "idUnidad", insertable = false, updatable = false)
    private Integer idUnidad;
    @Basic
    @Column(name = "observaciones")
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "idReceta", referencedColumnName = "idReceta")
    @JsonBackReference(value = "receta-utilizados")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recetasByIdReceta;
    @ManyToOne
    @JoinColumn(name = "idIngrediente", referencedColumnName = "idIngrediente")
    private Ingredient ingredientesByIdIngrediente;
    @ManyToOne
    @JoinColumn(name = "idUnidad", referencedColumnName = "idUnidad")
    private UnitOfMeasurement unidadesByIdUnidad;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientUsed utilizado = (IngredientUsed) o;

        if (!Objects.equals(idUtilizado, utilizado.idUtilizado))
            return false;
        if (!Objects.equals(idReceta, utilizado.idReceta)) return false;
        if (!Objects.equals(idIngrediente, utilizado.idIngrediente))
            return false;
        if (!Objects.equals(cantidad, utilizado.cantidad)) return false;
        if (!Objects.equals(idUnidad, utilizado.idUnidad)) return false;
        return Objects.equals(observaciones, utilizado.observaciones);
    }

    @Override
    public int hashCode() {
        int result = idUtilizado != null ? idUtilizado.hashCode() : 0;
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        result = 31 * result + (idIngrediente != null ? idIngrediente.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (idUnidad != null ? idUnidad.hashCode() : 0);
        result = 31 * result + (observaciones != null ? observaciones.hashCode() : 0);
        return result;
    }
}
