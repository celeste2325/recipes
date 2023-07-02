package com.distribuidas.recetas.modelo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "favoritos")
public class Favorito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "id_usuario", insertable = false, updatable = false)
    private Integer idUsuario;
    @Basic
    @Column(name = "id_receta", insertable = false, updatable = false)
    private Integer idReceta;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuariosByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "id_receta", referencedColumnName = "idReceta", nullable = false)
    private Receta recetasByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorito favorito = (Favorito) o;

        if (!Objects.equals(id, favorito.id)) return false;
        if (!Objects.equals(idUsuario, favorito.idUsuario)) return false;
        return Objects.equals(idReceta, favorito.idReceta);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "recetasByIdReceta=" + recetasByIdReceta +
                '}';
    }
}
