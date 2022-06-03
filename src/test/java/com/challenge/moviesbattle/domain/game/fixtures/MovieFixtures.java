package com.challenge.moviesbattle.domain.game.fixtures;

import com.challenge.moviesbattle.domain.game.dto.MovieDto;
import com.challenge.moviesbattle.domain.game.entities.Movie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieFixtures {

    public static Set<Movie> createMoviesDao(){
        Movie movie = new Movie();
//        movie.setId(1l);
        movie.setTitle("Batman");
        movie.setRatingValue(10.0);
        movie.setRatingCount(150.0);
        movie.setBest(movie.getRatingValue() * movie.getRatingCount());

        Movie movie2 = new Movie();
//        movie2.setId(2l);
        movie2.setTitle("Superman");
        movie2.setRatingValue(9.0);
        movie2.setRatingCount(150.0);
        movie2.setBest(movie2.getRatingValue() * movie2.getRatingCount());

        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);
        movies.add(movie2);
        return movies;
    }

    public static Set<Movie> createMoviesDao(final String movieParam1, final String movieParam2){
        Movie movie = new Movie();
//        movie.setId(1l);
        movie.setTitle(movieParam1);
        movie.setRatingValue(10.0);
        movie.setRatingCount(150.0);
        movie.setBest(movie.getRatingValue() * movie.getRatingCount());

        Movie movie2 = new Movie();
//        movie2.setId(2l);
        movie2.setTitle(movieParam2);
        movie2.setRatingValue(9.0);
        movie2.setRatingCount(150.0);
        movie2.setBest(movie2.getRatingValue() * movie2.getRatingCount());

        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);
        movies.add(movie2);
        return movies;
    }

    public static Movie createMovie(){
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("Batman");
        movie.setRatingValue(10.0);
        movie.setRatingValue(10.0);
        movie.setRatingCount(150.0);
        movie.setBest(movie.getRatingValue() * movie.getRatingCount());

        return movie;
    }

    public static MovieDto createBatmanMovieDto() {

        return MovieDto.builder()
            .title("Batman")
            .ratingValue(10.0)
            .build();
    }

    public static MovieDto createSupermanMovieDto() {

        return MovieDto.builder().title("Superman").ratingValue(9.0).build();
    }

    public static List<MovieDto> createMoviesDto() {

        return Arrays.asList(MovieDto.builder().title("Batman").ratingValue(10.0).ratingValue(150.0).build(),
            MovieDto.builder().title("Superman").ratingValue(9.0).ratingCount(150.0).build());
    }

    public static List<String> createMovies() {

        return Arrays.asList("Batman", "Superman");
    }


}
