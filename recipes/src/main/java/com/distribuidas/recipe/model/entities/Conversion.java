package com.distribuidas.recipe.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "conversions")
public class Conversion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "conversionID")
    private Integer conversionID;
    @Basic
    @Column(name = "fromUnitID", insertable = false, updatable = false)
    private Integer fromUnitID;
    @Basic
    @Column(name = "toUnitID", insertable = false, updatable = false)
    private Integer toUnitID;
    @Basic
    @Column(name = "conversionFactor")
    private Double conversionFactor;
    @ManyToOne
    @JoinColumn(name = "fromUnitID", referencedColumnName = "unitID", nullable = false)
    private UnitOfMeasurement unitOfMeasurementByFromUnitID;
    @ManyToOne
    @JoinColumn(name = "toUnitID", referencedColumnName = "unitID", nullable = false)
    private UnitOfMeasurement unitOfMeasurementByToUnitID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversion that = (Conversion) o;

        if (!Objects.equals(conversionID, that.conversionID)) return false;
        if (!Objects.equals(fromUnitID, that.fromUnitID))
            return false;
        if (!Objects.equals(toUnitID, that.toUnitID))
            return false;
        return Objects.equals(conversionFactor, that.conversionFactor);
    }

    @Override
    public int hashCode() {
        int result = conversionID != null ? conversionID.hashCode() : 0;
        result = 31 * result + (fromUnitID != null ? fromUnitID.hashCode() : 0);
        result = 31 * result + (toUnitID != null ? toUnitID.hashCode() : 0);
        result = 31 * result + (conversionFactor != null ? conversionFactor.hashCode() : 0);
        return result;
    }
}
