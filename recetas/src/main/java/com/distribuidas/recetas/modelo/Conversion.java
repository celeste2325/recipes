package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Conversion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idConversion")
    private Integer idConversion;
    @Basic
    @Column(name = "idUnidadOrigen", insertable=false, updatable=false)
    private Integer idUnidadOrigen;
    @Basic
    @Column(name = "idUnidadDestino", insertable=false, updatable=false)
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

        if (idConversion != null ? !idConversion.equals(that.idConversion) : that.idConversion != null) return false;
        if (idUnidadOrigen != null ? !idUnidadOrigen.equals(that.idUnidadOrigen) : that.idUnidadOrigen != null)
            return false;
        if (idUnidadDestino != null ? !idUnidadDestino.equals(that.idUnidadDestino) : that.idUnidadDestino != null)
            return false;
        if (factorConversiones != null ? !factorConversiones.equals(that.factorConversiones) : that.factorConversiones != null)
            return false;

        return true;
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
