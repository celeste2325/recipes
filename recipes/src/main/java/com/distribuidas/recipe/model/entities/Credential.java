package com.distribuidas.recipe.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "credentials")
public class Credential {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "userID")
    private Integer userID;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "verificationCode")
    private String verificationCode;
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    private User userByUserID;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Credential that = (Credential) o;

        if (!Objects.equals(id, that.id))
            return false;
        if (!Objects.equals(userID, that.userID))
            return false;
        if (!Objects.equals(password, that.password))
            return false;
        return Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (verificationCode != null ? verificationCode.hashCode() : 0);
        return result;
    }
}
