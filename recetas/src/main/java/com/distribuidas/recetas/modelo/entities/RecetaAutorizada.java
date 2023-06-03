package com.distribuidas.recetas.modelo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "RecetasAutorizadas", schema = "dbo", catalog = "recetas")
public class RecetaAutorizada {
    @Id
    @Column(name = "idReceta")
    private Integer idReceta;
    @Basic
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "foto")
    private String foto;
    @Basic
    @Column(name = "porciones")
    private Integer porciones;
    @Basic
    @Column(name = "cantidadPersonas")
    private Integer cantidadPersonas;
    @Basic
    @Column(name = "idTipo")
    private Integer idTipo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecetaAutorizada that = (RecetaAutorizada) o;

        if (idReceta != null ? !idReceta.equals(that.idReceta) : that.idReceta != null) return false;
        if (idUsuario != null ? !idUsuario.equals(that.idUsuario) : that.idUsuario != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (foto != null ? !foto.equals(that.foto) : that.foto != null) return false;
        if (porciones != null ? !porciones.equals(that.porciones) : that.porciones != null) return false;
        if (cantidadPersonas != null ? !cantidadPersonas.equals(that.cantidadPersonas) : that.cantidadPersonas != null)
            return false;
        if (idTipo != null ? !idTipo.equals(that.idTipo) : that.idTipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReceta != null ? idReceta.hashCode() : 0;
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        result = 31 * result + (porciones != null ? porciones.hashCode() : 0);
        result = 31 * result + (cantidadPersonas != null ? cantidadPersonas.hashCode() : 0);
        result = 31 * result + (idTipo != null ? idTipo.hashCode() : 0);
        return result;
    }

}
