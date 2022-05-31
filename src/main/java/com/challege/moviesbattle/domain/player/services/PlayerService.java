package com.challege.moviesbattle.domain.player.services;

import com.challege.moviesbattle.domain.jwt.entities.UserDao;
import com.challege.moviesbattle.domain.jwt.exceptions.UserNotFoundException;
import com.challege.moviesbattle.domain.jwt.reporsitories.UserRepository;
import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.dto.PlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import com.challege.moviesbattle.domain.player.exceptions.UserNameException;
import com.challege.moviesbattle.domain.player.mapper.PlayerMapper;
import com.challege.moviesbattle.domain.player.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * The type Player service.
 */
@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    private final PlayerMapper mapper;


    /**
     * Create player.
     *
     * @param createPlayerDto the create player dto
     * @return the player dto
     */
    @Transactional
    public PlayerDto createPlayer(final CreatePlayerDto createPlayerDto) {
        PlayerDto playerDto;

        if (createPlayerDto == null || StringUtils.isBlank(createPlayerDto.getUsername())) {
            throw new UserNameException();
        }
        UserDao userDao = userRepository.findByUsername(createPlayerDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException(createPlayerDto.getUsername()));

        playerDto = Objects.nonNull(getPlayer(createPlayerDto.getUsername())) ?
                mapper.from(getPlayer(createPlayerDto.getUsername())) :
                mapper.from(playerRepository.save(new Player(userDao.getId(), userDao.getUsername())));
        return playerDto;
    }

    private Player getPlayer(final String username) {
        return playerRepository.findByUsername(username).orElse(null);
    }

}
