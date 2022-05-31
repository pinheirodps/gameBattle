package com.challege.moviesbattle;

import com.challege.moviesbattle.config.TestSecurityConfig;
import de.cronn.testutils.h2.H2Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {TestSecurityConfig.class, MoviesBattleApplication.class})
@RunWith(SpringRunner.class)
@Import(H2Util.class)
public abstract class  AbstractIntegrationTest {

    @BeforeEach
    void resetDatabase(@Autowired H2Util h2Util) {
        h2Util.resetDatabase();
    }
}
