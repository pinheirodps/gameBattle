package com.challege.moviesbattle.infrasctructure;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.services.OmdbClientExternalService;
import com.challege.moviesbattle.infrasctructure.omdb.OmdbClientService;
import com.challege.moviesbattle.infrasctructure.omdb.mapper.OmdbClientMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OmdbClientServiceImpl implements OmdbClientExternalService {

    private final OmdbClientService omdbClientService;

    @Override
    public MovieDto findMovieByTitle(final String title) {
        return OmdbClientMapper.INSTANCE.moviesInfraToMoviesDto(omdbClientService.search(title));
    }

    @Override
    public List<MovieDto> getRandomMovies() {
        final List<String> moviesRandom = Arrays.asList("Batman", "SuperMan", "Inception", "Goodfellas");
        final List<MovieDto> movies = new ArrayList<>();

        // Create a Random object
        Random rand = new Random();

        // Shuffle/Randomize the list
        Collections.shuffle(moviesRandom, rand);

        moviesRandom.stream().skip(2).collect(Collectors.toList())
            .forEach(movie -> movies.add(this.findMovieByTitle(movie)));
        return movies;
    }
}
