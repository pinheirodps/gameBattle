package com.challege.moviesbattle.domain.game.entities;

import com.challege.moviesbattle.domain.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Movie extends BaseEntity {

    public Movie(final String title, final Double ratingValue, final Double ratingCount) {
        this.title = title;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
    }

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "imdb_rating")
    private Double ratingValue;
    @Column(name = "imdb_votes")
    private Double ratingCount;
    private Double best;
    @PrePersist
    protected void onCreate() {
        this.best = ratingCount * ratingValue;
    }
}
