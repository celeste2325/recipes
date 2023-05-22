package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Calificacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCalificacion")
    private Integer idCalificacion;
    @Basic
    @Column(name = "idusuario", insertable=false, updatable=false)
    private Integer idusuario;
    @Basic
    @Column(name = "idReceta", insertable=false, updatable=false)
    private Integer idReceta;
    @Basic
    @Column(name = "calificacion")
    private Integer calificacion;
    @Basic
    @Column(name = "comentarios")
    private String comentarios;
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idUsuario")
    private Usuario usuariosByIdusuario;
    @ManyToOne
    @JoinColumn(name = "idReceta", referencedColumnName = "idReceta")
    private Receta recetasByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calificacion that = (Calificacion) o;

        if (idCalificacion != null ? !idCalificacion.equals(that.idCalificacion) : that.idCalificacion != null)
            return false;
        if (idusuario != null ? !idusuario.equals(that.idusuario) : that.idusuario != null) return false;
        if (idReceta != null ? !idReceta.equals(that.idReceta) : that.idReceta != null) return false;
        if (calificacion != null ? !calificacion.equals(that.calificacion) : that.calificacion != null) return false;
        if (comentarios != null ? !comentarios.equals(that.comentarios) : that.comentarios != null) return false;

        return true;
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
