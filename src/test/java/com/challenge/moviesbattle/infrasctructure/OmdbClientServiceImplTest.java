package com.challenge.moviesbattle.infrasctructure;

import com.challenge.moviesbattle.domain.movie.repositories.MovieRepository;
import com.challenge.moviesbattle.domain.ranking.services.RankingServices;
import com.challenge.moviesbattle.infrasctructure.omdb.OmdbClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class OmdbClientServiceImplTest {


    @Autowired
    OmdbClientService omdbClientService;
    @Autowired(required = false)
    private MovieRepository movieRepository;
    @Autowired
    RankingServices rankingServices;

    @Test
    public void testFindMoviesByTitle() throws Exception {

//        ResultSearch resultSearch = this.omdbClientService.search("Batman");

    }

}

