package com.distribuidas.recipe.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "reviews")
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reviewID")
    private Integer reviewID;
    @Basic
    @Column(name = "userID", insertable = false, updatable = false)
    private Integer userID;
    @Basic
    @Column(name = "recipeID", insertable = false, updatable = false)
    private Integer recipeID;
    @Basic
    @Column(name = "starts")
    private Integer rating;
    @Basic
    @Column(name = "details")
    private String comment;
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

        Review that = (Review) o;

        if (!Objects.equals(reviewID, that.reviewID))
            return false;
        if (!Objects.equals(userID, that.userID)) return false;
        if (!Objects.equals(recipeID, that.recipeID)) return false;
        if (!Objects.equals(rating, that.rating)) return false;
        return Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        int result = reviewID != null ? reviewID.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (recipeID != null ? recipeID.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
