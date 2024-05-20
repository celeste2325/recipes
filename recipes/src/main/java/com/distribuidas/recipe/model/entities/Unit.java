package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "units")
public class Unit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "unitID")
    private Integer unitID;
    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "unitOfMeasurementByFromUnitID")
    private Collection<Conversion> conversionsByUnitID;

    @OneToMany(mappedBy = "unitOfMeasurementByToUnitID")
    private Collection<Conversion> conversionsByUnitID_0;

    @OneToMany(mappedBy = "unitsOfMeasurementByUnitID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<IngredientUsed> ingredientUsedByUnitID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        if (!Objects.equals(unitID, unit.unitID)) return false;
        return Objects.equals(description, unit.description);
    }

    @Override
    public int hashCode() {
        int result = unitID != null ? unitID.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
