package com.challege.moviesbattle.domain.game.fixtures;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.entities.Movie;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieFixtures {

    public static Set<Movie> createMovies(){
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("Batman");
        movie.setImdbRating("10");

        Movie movie2 = new Movie();
        movie2.setId(2l);
        movie2.setTitle("Superman");
        movie2.setImdbRating("9");

        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);
        movies.add(movie2);
        return movies;
    }

    public static Movie createMovie(){
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("Batman");
        movie.setImdbRating("10");

        return movie;
    }

    public static MovieDto createBatmanMovieDto() {

        return MovieDto.builder()
            .title("Batman")
            .imdbRating("10")
            .build();
    }

    public static MovieDto createSupermanMovieDto() {

        return MovieDto.builder().title("Superman").imdbRating("9").build();
    }

    public static List<MovieDto> createMoviesDto() {

        return Arrays.asList(MovieDto.builder().title("Batman").imdbRating("10").build(),
            MovieDto.builder().title("Superman").imdbRating("9").build());
    }


}
