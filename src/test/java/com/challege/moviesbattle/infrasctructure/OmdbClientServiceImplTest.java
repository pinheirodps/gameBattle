package com.challege.moviesbattle.infrasctructure;

import com.challege.moviesbattle.domain.movie.repositories.MovieRepository;
import com.challege.moviesbattle.domain.ranking.services.RankingServices;
import com.challege.moviesbattle.infrasctructure.omdb.OmdbClientService;
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

