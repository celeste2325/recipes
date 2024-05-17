package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "pasos")
public class Step {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPaso")
    private Integer idPaso;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @Basic
    @Column(name = "nroPaso")
    private Integer nroPaso;
    @Basic
    @Column(name = "texto")
    private String texto;
    @OneToMany(mappedBy = "pasosByIdPaso")
    private Collection<Multimedia> multimediaByIdPaso;
    @ManyToOne
    @JoinColumn(name = "recipeID", referencedColumnName = "recipeID")
    @JsonBackReference(value = "receta-pasos")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recipeByRecipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step paso = (Step) o;

        if (!Objects.equals(idPaso, paso.idPaso)) return false;
        if (!Objects.equals(recipeID, paso.recipeID)) return false;
        if (!Objects.equals(nroPaso, paso.nroPaso)) return false;
        return Objects.equals(texto, paso.texto);
    }

    @Override
    public int hashCode() {
        int result = idPaso != null ? idPaso.hashCode() : 0;
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        result = 31 * result + (nroPaso != null ? nroPaso.hashCode() : 0);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        return result;
    }
}