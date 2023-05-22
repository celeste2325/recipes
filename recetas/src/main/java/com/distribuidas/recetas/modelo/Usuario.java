package com.distribuidas.recetas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Setter
@Getter
@Table(name = "usuarios", schema = "dbo", catalog = "recetas")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "habilitado")
    private String habilitado;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @OneToMany(mappedBy = "usuariosByIdusuario")
    private Collection<Calificacion> calificacionesByIdUsuario;
    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<Credencial> credencialesByIdUsuario;
    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<Favorito> favoritosByIdUsuario;
    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<Receta> recetasByIdUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (idUsuario != null ? !idUsuario.equals(usuario.idUsuario) : usuario.idUsuario != null) return false;
        if (mail != null ? !mail.equals(usuario.mail) : usuario.mail != null) return false;
        if (nickname != null ? !nickname.equals(usuario.nickname) : usuario.nickname != null) return false;
        if (habilitado != null ? !habilitado.equals(usuario.habilitado) : usuario.habilitado != null) return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
        if (avatar != null ? !avatar.equals(usuario.avatar) : usuario.avatar != null) return false;
        if (tipoUsuario != null ? !tipoUsuario.equals(usuario.tipoUsuario) : usuario.tipoUsuario != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = idUsuario != null ? idUsuario.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (habilitado != null ? habilitado.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (tipoUsuario != null ? tipoUsuario.hashCode() : 0);
        return result;
    }
}
