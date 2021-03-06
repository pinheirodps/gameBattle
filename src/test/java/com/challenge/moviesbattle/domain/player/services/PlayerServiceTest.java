package com.challenge.moviesbattle.domain.player.services;

import com.challenge.moviesbattle.domain.game.fixtures.PlayerFixtures;
import com.challenge.moviesbattle.domain.jwt.entities.UserDao;
import com.challenge.moviesbattle.domain.jwt.exceptions.UserNotFoundException;
import com.challenge.moviesbattle.domain.jwt.reporsitories.UserRepository;
import com.challenge.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challenge.moviesbattle.domain.player.dto.PlayerDto;
import com.challenge.moviesbattle.domain.player.entities.Player;
import com.challenge.moviesbattle.domain.player.exceptions.UserNameException;
import com.challenge.moviesbattle.domain.player.mapper.PlayerMapper;
import com.challenge.moviesbattle.domain.player.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlayerServiceTest {
    @Mock
    PlayerRepository playerRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    PlayerMapper mapper;
    @InjectMocks
    PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @CsvSource(value = {"Danilo", "new"})
    void testCreatePlayer(final String username) {
        UserDao userDao = new UserDao();
        userDao.setUsername("Danilo");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userDao));
        when(mapper.from(any())).thenReturn(PlayerFixtures.createPlayerDto());

        if (username.equals("new")){
            when(playerRepository.findByUsername(anyString())).thenReturn(Optional.empty());
            PlayerDto result = playerService.createPlayer(new CreatePlayerDto(username));
            assertNotNull(result);
            assertThat(result.getUserId()).isEqualTo("1");
            verify(playerRepository, times(1)).save(any(Player.class));
        } else {
            when(playerRepository.findByUsername(anyString())).thenReturn(Optional.of(PlayerFixtures.createPlayer()));
            PlayerDto result = playerService.createPlayer(new CreatePlayerDto(username));
            assertNotNull(result);
            assertThat(result.getUserId()).isEqualTo("1");
            verify(playerRepository, times(0)).save(any(Player.class));
            verify(playerRepository, times(0)).findById(eq("Danilo"));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"Danilo", "null"}, nullValues = "null")
    void testCreatePlayerException(final String username) {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        if (username == null){
            assertThrows(UserNameException.class, ()-> playerService
                    .createPlayer(CreatePlayerDto.builder().username(null).build()));
        }else {
            assertThrows(UserNotFoundException.class, () -> playerService
                    .createPlayer(CreatePlayerDto.builder().username(username).build()));
        }
    }
}
