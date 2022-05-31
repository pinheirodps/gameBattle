package com.challege.moviesbattle;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;


class MoviesBattleApplicationTests {

    @Test
    void contextLoads() {
        // Create a List with all your items

//        List<MovieDto> movies = Arrays.asList(MovieDto.builder().title("Batman").imdbRating(9.0).build(),
//            MovieDto.builder().title("SuperMan").imdbRating(10.0).build(),
//            MovieDto.builder().title("Inception").imdbRating("8").build(),
//            MovieDto.builder().title("Goodfellas").imdbRating(10.0).build()
//
//        );

        List<String> movies = Arrays.asList("Batman", "SuperMan", "Inception", "Goodfellas");

        // Create a Random object
        Random rand = new Random();

        // Shuffle/Randomize the list
        Collections.shuffle(movies, rand);

        movies.stream().skip(2).collect(Collectors.toList())
            .forEach(System.out::println);
    }




}
