package com.distribuidas.recetas.modelo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Setter
@Getter
@Table(name = "pasos")
public class Paso {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPaso")
    private Integer idPaso;
    @Basic
    @Column(name = "idReceta", insertable = false, updatable = false)
    private Integer idReceta;
    @Basic
    @Column(name = "nroPaso")
    private Integer nroPaso;
    @Basic
    @Column(name = "texto")
    private String texto;
    @OneToMany(mappedBy = "pasosByIdPaso")
    private Collection<Multimedia> multimediaByIdPaso;
    @ManyToOne
    @JoinColumn(name = "idReceta", referencedColumnName = "idReceta")
    @JsonBackReference(value = "receta-pasos")
    private Receta recetasByIdReceta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paso paso = (Paso) o;

        if (idPaso != null ? !idPaso.equals(paso.idPaso) : paso.idPaso != null) return false;
        if (idReceta != null ? !idReceta.equals(paso.idReceta) : paso.idReceta != null) return false;
        if (nroPaso != null ? !nroPaso.equals(paso.nroPaso) : paso.nroPaso != null) return false;
        if (texto != null ? !texto.equals(paso.texto) : paso.texto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaso != null ? idPaso.hashCode() : 0;
        result = 31 * result + (idReceta != null ? idReceta.hashCode() : 0);
        result = 31 * result + (nroPaso != null ? nroPaso.hashCode() : 0);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        return result;
    }
}
