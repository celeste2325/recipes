package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Column(name = "idUsuario", insertable = false, updatable = false)
    private Integer idUsuario;
    @Basic
    @Column(name = "contrasenia")
    private String contrasenia;
    @Basic
    @Column(name = "codigo_verificacion")
    private String codigoVerificacion;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuariosByIdUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credencial that = (Credencial) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idUsuario != null ? !idUsuario.equals(that.idUsuario) : that.idUsuario != null) return false;
        if (contrasenia != null ? !contrasenia.equals(that.contrasenia) : that.contrasenia != null) return false;
        if (codigoVerificacion != null ? !codigoVerificacion.equals(that.codigoVerificacion) : that.codigoVerificacion != null)
            return false;

        return true;
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
