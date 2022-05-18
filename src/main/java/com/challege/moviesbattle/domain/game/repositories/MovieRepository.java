package com.challege.moviesbattle.domain.game.repositories;

import com.challege.moviesbattle.domain.game.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
