package com.challege.moviesbattle.infrasctructure;

import com.challege.moviesbattle.infrasctructure.omdb.OmdbClientService;
import com.challege.moviesbattle.infrasctructure.omdb.ResultSearch;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OmdbClientServiceImplTest {


    @Autowired
    OmdbClientService omdbClientService;


    @Test
    public void testFindMoviesByTitle() throws Exception {

        ResultSearch resultSearch = this.omdbClientService.search("lord of the rings");
        assertTrue(resultSearch.getResponse());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme