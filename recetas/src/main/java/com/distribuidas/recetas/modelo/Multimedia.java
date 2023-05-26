package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Multimedia")
public class Multimedia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idContenido")
    private Integer idContenido;
    @Basic
    @Column(name = "idPaso", insertable = false, updatable = false)
    private Integer idPaso;
    @Basic
    @Column(name = "tipo_contenido")
    private String tipoContenido;
    @Basic
    @Column(name = "extension")
    private String extension;
    @Basic
    @Column(name = "urlContenido")
    private String urlContenido;
    @ManyToOne
    @JoinColumn(name = "idPaso", referencedColumnName = "idPaso", nullable = false)
    private Paso pasosByIdPaso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multimedia that = (Multimedia) o;

        if (idContenido != null ? !idContenido.equals(that.idContenido) : that.idContenido != null) return false;
        if (idPaso != null ? !idPaso.equals(that.idPaso) : that.idPaso != null) return false;
        if (tipoContenido != null ? !tipoContenido.equals(that.tipoContenido) : that.tipoContenido != null)
            return false;
        if (extension != null ? !extension.equals(that.extension) : that.extension != null) return false;
        if (urlContenido != null ? !urlContenido.equals(that.urlContenido) : that.urlContenido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContenido != null ? idContenido.hashCode() : 0;
        result = 31 * result + (idPaso != null ? idPaso.hashCode() : 0);
        result = 31 * result + (tipoContenido != null ? tipoContenido.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (urlContenido != null ? urlContenido.hashCode() : 0);
        return result;
    }
}
