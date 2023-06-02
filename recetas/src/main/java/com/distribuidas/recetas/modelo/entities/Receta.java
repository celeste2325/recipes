package com.distribuidas.recetas.modelo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "recetas")
public class Receta {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idReceta")
    private Integer idReceta;
    @Basic
    @Column(name = "idUsuario", insertable = false, updatable = false)
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
    @Column(name = "idTipo", insertable = false, updatable = false)
    private Integer idTipo;
    @OneToMany(mappedBy = "recetasByIdReceta")
    private Collection<Calificacion> calificacionesByIdReceta;
    @OneToMany(mappedBy = "recetasByIdReceta")
    private Collection<Favorito> favoritosByIdReceta;
    @OneToMany(mappedBy = "recetasByIdReceta", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Foto> fotosByIdReceta;
    @OneToMany(mappedBy = "recetasByIdReceta", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Paso> pasosByIdReceta;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @JsonBackReference(value = "receta-usuario")
    private Usuario usuariosByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "idTipo", referencedColumnName = "idTipo")
    @JsonBackReference(value = "receta-tipo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tipo tiposByIdTipo;
    @OneToMany(mappedBy = "recetasByIdReceta", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Utilizado> utilizadosByIdReceta;

    @OneToOne(mappedBy = "recetaByIdReceta", cascade = CascadeType.ALL)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private FechaReceta fechaRecetaByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receta receta = (Receta) o;

        if (idReceta != null ? !idReceta.equals(receta.idReceta) : receta.idReceta != null) return false;
        if (idUsuario != null ? !idUsuario.equals(receta.idUsuario) : receta.idUsuario != null) return false;
        if (nombre != null ? !nombre.equals(receta.nombre) : receta.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(receta.descripcion) : receta.descripcion != null) return false;
        if (foto != null ? !foto.equals(receta.foto) : receta.foto != null) return false;
        if (porciones != null ? !porciones.equals(receta.porciones) : receta.porciones != null) return false;
        if (cantidadPersonas != null ? !cantidadPersonas.equals(receta.cantidadPersonas) : receta.cantidadPersonas != null)
            return false;
        if (idTipo != null ? !idTipo.equals(receta.idTipo) : receta.idTipo != null) return false;

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

    @Override
    public String toString() {
        return "Receta{" +
                "idReceta=" + idReceta +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", porciones=" + porciones +
                ", cantidadPersonas=" + cantidadPersonas +
                ", fotosByIdReceta=" + fotosByIdReceta +
                ", pasosByIdReceta=" + pasosByIdReceta +
                ", tiposByIdTipo=" + tiposByIdTipo +
                ", utilizadosByIdReceta=" + utilizadosByIdReceta +
                '}';
    }
}
