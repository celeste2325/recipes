package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
    @Column(name = "stepID", insertable = false, updatable = false)
    private Integer stepID;
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
    @JoinColumn(name = "stepID", referencedColumnName = "stepID", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Step stepByStepID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multimedia that = (Multimedia) o;

        if (!Objects.equals(idContenido, that.idContenido)) return false;
        if (!Objects.equals(stepID, that.stepID)) return false;
        if (!Objects.equals(tipoContenido, that.tipoContenido))
            return false;
        if (!Objects.equals(extension, that.extension)) return false;
        return Objects.equals(urlContenido, that.urlContenido);
    }

    @Override
    public int hashCode() {
        int result = idContenido != null ? idContenido.hashCode() : 0;
        result = 31 * result + (stepID != null ? stepID.hashCode() : 0);
        result = 31 * result + (tipoContenido != null ? tipoContenido.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (urlContenido != null ? urlContenido.hashCode() : 0);
        return result;
    }
}
