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
@Table(name = "steps")
public class Step {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stepID")
    private Integer stepID;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @Basic
    @Column(name = "stepNumber")
    private Integer stepNumber;
    @Basic
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "stepByStepID")
    private Collection<Multimedia> multimediaByStepID;
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

        if (!Objects.equals(stepID, paso.stepID)) return false;
        if (!Objects.equals(recipeID, paso.recipeID)) return false;
        if (!Objects.equals(stepNumber, paso.stepNumber)) return false;
        return Objects.equals(description, paso.description);
    }

    @Override
    public int hashCode() {
        int result = stepID != null ? stepID.hashCode() : 0;
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        result = 31 * result + (stepNumber != null ? stepNumber.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
