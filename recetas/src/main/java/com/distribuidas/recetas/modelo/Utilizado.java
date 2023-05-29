package com.distribuidas.recetas.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "utilizados")
public class Utilizado {
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
    private Receta recetasByIdReceta;
    @ManyToOne
    @JoinColumn(name = "idIngrediente", referencedColumnName = "idIngrediente")
    private Ingrediente ingredientesByIdIngrediente;
    @ManyToOne
    @JoinColumn(name = "idUnidad", referencedColumnName = "idUnidad")
    private Unidad unidadesByIdUnidad;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilizado utilizado = (Utilizado) o;

        if (idUtilizado != null ? !idUtilizado.equals(utilizado.idUtilizado) : utilizado.idUtilizado != null)
            return false;
        if (idReceta != null ? !idReceta.equals(utilizado.idReceta) : utilizado.idReceta != null) return false;
        if (idIngrediente != null ? !idIngrediente.equals(utilizado.idIngrediente) : utilizado.idIngrediente != null)
            return false;
        if (cantidad != null ? !cantidad.equals(utilizado.cantidad) : utilizado.cantidad != null) return false;
        if (idUnidad != null ? !idUnidad.equals(utilizado.idUnidad) : utilizado.idUnidad != null) return false;
        if (observaciones != null ? !observaciones.equals(utilizado.observaciones) : utilizado.observaciones != null)
            return false;

        return true;
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
