package com.distribuidas.recetas.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "credenciales")
public class Credencial {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic
    @Column(name = "contrasenia")
    private String contrasenia;
    @Basic
    @Column(name = "codigo_verificacion")
    private String codigoVerificacion;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    private Usuario usuariosByIdUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Credencial that = (Credencial) o;

        if (!Objects.equals(id, that.id))
            return false;
        if (!Objects.equals(idUsuario, that.idUsuario))
            return false;
        if (!Objects.equals(contrasenia, that.contrasenia))
            return false;
        return Objects.equals(codigoVerificacion, that.codigoVerificacion);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        result = 31 * result + (contrasenia != null ? contrasenia.hashCode() : 0);
        result = 31 * result + (codigoVerificacion != null ? codigoVerificacion.hashCode() : 0);
        return result;
    }
}
