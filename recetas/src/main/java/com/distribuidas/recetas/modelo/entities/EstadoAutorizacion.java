package com.distribuidas.recetas.modelo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "estados_autorizaciones")
public class EstadoAutorizacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "id_entidad")
    private Integer idEntidad;
    @Basic
    @Column(name = "tipo_estado")
    private String tipoEstado;
    @Basic
    @Column(name = "tipo_entidad")
    private String tipoEntidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoAutorizacion that = (EstadoAutorizacion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idEntidad != null ? !idEntidad.equals(that.idEntidad) : that.idEntidad != null) return false;
        if (tipoEstado != null ? !tipoEstado.equals(that.tipoEstado) : that.tipoEstado != null) return false;
        if (tipoEntidad != null ? !tipoEntidad.equals(that.tipoEntidad) : that.tipoEntidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idEntidad != null ? idEntidad.hashCode() : 0);
        result = 31 * result + (tipoEstado != null ? tipoEstado.hashCode() : 0);
        result = 31 * result + (tipoEntidad != null ? tipoEntidad.hashCode() : 0);
        return result;
    }
}
