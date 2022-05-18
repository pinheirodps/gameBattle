package com.challege.moviesbattle.domain.player.services;

import com.challege.moviesbattle.domain.jwt.entities.UserDao;
import com.challege.moviesbattle.domain.jwt.reporsitories.UserRepository;
import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import com.challege.moviesbattle.domain.player.exceptions.UserNameException;
import com.challege.moviesbattle.domain.player.repositories.PlayerRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;
    private UserRepository userRepository;


    @Transactional
    public Player createPlayer(final CreatePlayerDto createPlayerDto) {
        if (createPlayerDto == null || StringUtils.isBlank(createPlayerDto.getUsername())) {
            throw new UserNameException();
        }
        UserDao userDao = userRepository.findByUsername(createPlayerDto.getUsername());
        Player player = new Player(String.valueOf(userDao.getId()), userDao.getUsername());
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayer(final String username) {
        return playerRepository.findByUsername(username);
    }

}
