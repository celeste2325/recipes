package com.distribuidas.recetas.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;


@Entity
@Setter
@Getter
@Table(name = "usuarios")
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
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuariosByIdusuario")
    private Collection<Calificacion> calificacionesByIdUsuario;
    //@JsonIgnore
    //@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    //@JoinColumn(name="idUsuario")
    //@JsonIgnore
    // this should be only one ? one to one relation ?

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<Credencial> credencialesByIdUsuario;
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<Favorito> favoritosByIdUsuario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<Receta> recetasByIdUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!Objects.equals(idUsuario, usuario.idUsuario)) return false;
        if (!Objects.equals(mail, usuario.mail)) return false;
        if (!Objects.equals(nickname, usuario.nickname)) return false;
        if (!Objects.equals(habilitado, usuario.habilitado)) return false;
        if (!Objects.equals(nombre, usuario.nombre)) return false;
        if (!Objects.equals(avatar, usuario.avatar)) return false;
        return Objects.equals(tipoUsuario, usuario.tipoUsuario);
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
