package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "favoritos", schema = "dbo", catalog = "recetas")
public class Favorito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "id_usuario", insertable=false, updatable=false)
    private Integer idUsuario;
    @Basic
    @Column(name = "id_receta", insertable=false, updatable=false)
    private Integer idReceta;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario", nullable = false)
    private Usuario usuariosByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "id_receta", referencedColumnName = "idReceta", nullable = false)
    private Receta recetasByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorito favorito = (Favorito) o;

        if (id != null ? !id.equals(favorito.id) : favorito.id != null) return false;
        if (idUsuario != null ? !idUsuario.equals(favorito.idUsuario) : favorito.idUsuario != null) return false;
        if (idReceta != null ? !idReceta.equals(favorito.idReceta) : favorito.idReceta != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        return result;
    }
}
