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
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userID")
    private Integer userID;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "enabled")
    private String enabled;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "type_user")
    private String type_user;
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userByUserID")
    private Collection<Rating> ratingsByUserID;
    //@JsonIgnore
    //@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    //@JoinColumn(name="idUsuario")
    //@JsonIgnore
    // this should be only one ? one to one relation ?

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userByUserID")
    private Collection<Credential> credentialsByUserID;
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usersByUserID")
    private Collection<Favorite> favoritesByUserID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userByUserID")
    private Collection<Recipe> recipesByUserID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(userID, user.userID)) return false;
        if (!Objects.equals(mail, user.mail)) return false;
        if (!Objects.equals(nickname, user.nickname)) return false;
        if (!Objects.equals(enabled, user.enabled)) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(avatar, user.avatar)) return false;
        return Objects.equals(type_user, user.type_user);
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (type_user != null ? type_user.hashCode() : 0);
        return result;
    }


}
