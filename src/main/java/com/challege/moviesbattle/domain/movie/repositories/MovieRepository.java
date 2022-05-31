package com.challege.moviesbattle.domain.movie.repositories;

import com.challege.moviesbattle.domain.game.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Movie repository.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
    /**
     * Find movie by title optional.
     *
     * @param title the title
     * @return the optional
     */
    Optional<Movie>findMovieByTitle(final String title);
}
