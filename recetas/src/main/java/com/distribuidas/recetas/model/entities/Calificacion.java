package com.distribuidas.recetas.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "calificaciones")
public class Calificacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCalificacion")
    private Integer idCalificacion;
    @Basic
    @Column(name = "idusuario", insertable = false, updatable = false)
    private Integer idusuario;
    @Basic
    @Column(name = "idReceta", insertable = false, updatable = false)
    private Integer idReceta;
    @Basic
    @Column(name = "calificacion")
    private Integer calificacion;
    @Basic
    @Column(name = "comentarios")
    private String comentarios;
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idUsuario")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuariosByIdusuario;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "idReceta", referencedColumnName = "idReceta")
    private Receta recetasByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calificacion that = (Calificacion) o;

        if (!Objects.equals(idCalificacion, that.idCalificacion))
            return false;
        if (!Objects.equals(idusuario, that.idusuario)) return false;
        if (!Objects.equals(idReceta, that.idReceta)) return false;
        if (!Objects.equals(calificacion, that.calificacion)) return false;
        return Objects.equals(comentarios, that.comentarios);
    }

    @Override
    public int hashCode() {
        int result = idCalificacion != null ? idCalificacion.hashCode() : 0;
        result = 31 * result + (idusuario != null ? idusuario.hashCode() : 0);
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        result = 31 * result + (calificacion != null ? calificacion.hashCode() : 0);
        result = 31 * result + (comentarios != null ? comentarios.hashCode() : 0);
        return result;
    }
}
