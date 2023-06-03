package com.distribuidas.recetas.modelo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(idEntidad, that.idEntidad)) return false;
        if (!Objects.equals(tipoEstado, that.tipoEstado)) return false;
        return Objects.equals(tipoEntidad, that.tipoEntidad);
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
