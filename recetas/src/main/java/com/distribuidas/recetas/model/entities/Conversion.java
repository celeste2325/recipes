package com.distribuidas.recetas.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "conversiones")
public class Conversion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idConversion")
    private Integer idConversion;
    @Basic
    @Column(name = "idUnidadOrigen", insertable = false, updatable = false)
    private Integer idUnidadOrigen;
    @Basic
    @Column(name = "idUnidadDestino", insertable = false, updatable = false)
    private Integer idUnidadDestino;
    @Basic
    @Column(name = "factorConversiones")
    private Double factorConversiones;
    @ManyToOne
    @JoinColumn(name = "idUnidadOrigen", referencedColumnName = "idUnidad", nullable = false)
    private Unidad unidadesByIdUnidadOrigen;
    @ManyToOne
    @JoinColumn(name = "idUnidadDestino", referencedColumnName = "idUnidad", nullable = false)
    private Unidad unidadesByIdUnidadDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversion that = (Conversion) o;

        if (!Objects.equals(idConversion, that.idConversion)) return false;
        if (!Objects.equals(idUnidadOrigen, that.idUnidadOrigen))
            return false;
        if (!Objects.equals(idUnidadDestino, that.idUnidadDestino))
            return false;
        return Objects.equals(factorConversiones, that.factorConversiones);
    }

    @Override
    public int hashCode() {
        int result = idConversion != null ? idConversion.hashCode() : 0;
        result = 31 * result + (idUnidadOrigen != null ? idUnidadOrigen.hashCode() : 0);
        result = 31 * result + (idUnidadDestino != null ? idUnidadDestino.hashCode() : 0);
        result = 31 * result + (factorConversiones != null ? factorConversiones.hashCode() : 0);
        return result;
    }
}
