package com.distribuidas.recipe.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "authorization_status")
public class AuthorizationStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "entityID")
    private Integer entityID;
    @Basic
    @Column(name = "status_type")
    private String statusType;
    @Basic
    @Column(name = "entity_type")
    private String entityType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizationStatus that = (AuthorizationStatus) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(entityID, that.entityID)) return false;
        if (!Objects.equals(statusType, that.statusType)) return false;
        return Objects.equals(entityType, that.entityType);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entityID != null ? entityID.hashCode() : 0);
        result = 31 * result + (statusType != null ? statusType.hashCode() : 0);
        result = 31 * result + (entityType != null ? entityType.hashCode() : 0);
        return result;
    }
}
