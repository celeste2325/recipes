package com.distribuidas.recetas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "fotos", schema = "dbo", catalog = "recetas")
public class Foto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idfoto")
    private Integer idfoto;
    @Basic
    @Column(name = "idReceta", insertable=false, updatable=false)
    private Integer idReceta;
    @Basic
    @Column(name = "urlFoto")
    private String urlFoto;
    @Basic
    @Column(name = "extension")
    private String extension;
    @ManyToOne
    @JoinColumn(name = "idReceta", referencedColumnName = "idReceta", nullable = false)
    private Receta recetasByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foto foto = (Foto) o;

        if (idfoto != null ? !idfoto.equals(foto.idfoto) : foto.idfoto != null) return false;
        if (idReceta != null ? !idReceta.equals(foto.idReceta) : foto.idReceta != null) return false;
        if (urlFoto != null ? !urlFoto.equals(foto.urlFoto) : foto.urlFoto != null) return false;
        if (extension != null ? !extension.equals(foto.extension) : foto.extension != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idfoto != null ? idfoto.hashCode() : 0;
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        result = 31 * result + (urlFoto != null ? urlFoto.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
}
