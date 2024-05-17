package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "ratings")
public class Rating {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ratingID")
    private Integer ratingID;
    @Basic
    @Column(name = "userID", insertable = false, updatable = false)
    private Integer userID;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @Basic
    @Column(name = "starts")
    private Integer starts;
    @Basic
    @Column(name = "details")
    private String details;
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User userByUserID;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "recipeID", referencedColumnName = "recipeID")
    private Recipe recipeByRecipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating that = (Rating) o;

        if (!Objects.equals(ratingID, that.ratingID))
            return false;
        if (!Objects.equals(userID, that.userID)) return false;
        if (!Objects.equals(recipeID, that.recipeID)) return false;
        if (!Objects.equals(starts, that.starts)) return false;
        return Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        int result = ratingID != null ? ratingID.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        result = 31 * result + (starts != null ? starts.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
}
