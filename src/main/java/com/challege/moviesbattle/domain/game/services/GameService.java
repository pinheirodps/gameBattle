package com.challege.moviesbattle.domain.game.services;

import com.challege.moviesbattle.domain.game.dto.CreateGameDto;
import com.challege.moviesbattle.domain.game.dto.GameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.game.entities.Movie;
import com.challege.moviesbattle.domain.game.entities.PlayerGameData;
import com.challege.moviesbattle.domain.game.enums.GameStatus;
import com.challege.moviesbattle.domain.game.exceptions.GameNotFoundException;
import com.challege.moviesbattle.domain.game.exceptions.GameOverException;
import com.challege.moviesbattle.domain.game.mapper.GameMapper;
import com.challege.moviesbattle.domain.game.repositories.GameRepository;
import com.challege.moviesbattle.domain.game.repositories.MovieRepository;
import com.challege.moviesbattle.domain.game.repositories.PlayerGameDataRepository;
import com.challege.moviesbattle.domain.player.dto.CreateJoinGameDto;
import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import com.challege.moviesbattle.domain.player.exceptions.PlayerNotFoundException;
import com.challege.moviesbattle.domain.player.repositories.PlayerRepository;
import com.challege.moviesbattle.domain.player.services.PlayerService;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GameService {

    private final OmdbClientExternalService omdbClientExternalService;
    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final PlayerGameDataRepository playerGameDataRepository;
    private final PlayerRepository playerRepository;

    private final MovieRepository movieRepository;

    @Transactional
    public void createGame(CreateGameDto createGameDto) {
        Optional<Player> player = getPlayer(createGameDto.getUsername());
        player.ifPresent(this::createNewGame);
    }

    private void createNewGame(final Player player) {
        GameDto gameDto;
        Game gameToSave = new Game();
        gameToSave.setGameStatus(GameStatus.OPEN);
        gameToSave.setPlayer(player);
        gameDto = GameMapper.INSTANCE.from(gameRepository.save(gameToSave));

        final List<MovieDto> movies = omdbClientExternalService.getRandomMovies();
        Game gameUpdated = gameRepository.findById(gameDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(String.valueOf(gameDto.getGameId())));

        saveMoviesDaoAndUpdateGame(movies, gameUpdated);

    }

    private void saveMoviesDaoAndUpdateGame(final List<MovieDto> movies, final Game game) {
        Set<Movie> moviesDao = movies.stream()
            .map(movieDto -> new Movie(movieDto.getTitle(), movieDto.getImdbRating(), game))
            .collect(Collectors.toSet());
        movieRepository.saveAll(moviesDao);
        game.setMovies(moviesDao);
        gameRepository.save(game);
    }


    private Optional<Player> getPlayer(final String username) {
        return playerService.getPlayer(username);
    }


    @Transactional
    public void joinGame(final String gameId, final CreateJoinGameDto createJoinGameDto) {
        Optional<Player> player = getPlayer(createJoinGameDto.getUsername());
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        if (player.isPresent()) {
//            player.get().setGame(game);
//            playerRepository.save(player.get());

            PlayerGameData playerGameData = new PlayerGameData();
            playerGameData.setPlayer(player.get());
            playerGameData.setGame(game);
            playerGameDataRepository.save(playerGameData);

            game.setGameStatus(GameStatus.READY);
             gameRepository.save(game);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GameInfoDto play(final String gameId, final CreatePlayerDto playerDto) {
        GameInfoDto gameInfoDto;
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        PlayerGameData playerGameData = playerGameDataRepository.findByGame(game)
            .orElseThrow(() -> new IllegalArgumentException());

        game.setGameStatus(GameStatus.PLAYING);

        Movie movie = game.getMovies().stream().max(Comparator.comparing(Movie::getImdbRating))
            .orElseThrow(NoSuchElementException::new);
        if (playerDto.getMovieName().equalsIgnoreCase(movie.getTitle())) {
            playerGameData.setScore(playerGameData.getScore() + 1);
            playerGameDataRepository.save(playerGameData);
            saveMoviesDaoAndUpdateGame(omdbClientExternalService.getRandomMovies(), game);
        } else {
            game.setCurrentRound(game.getCurrentRound() + 1);
        }
        if (game.getCurrentRound() > 3) {
            game.setGameStatus(GameStatus.ENDED);
            gameRepository.save(game);
            throw new GameOverException(game.getId());
        }
        gameInfoDto = getGame(gameId, "playerId");

        return gameInfoDto;
    }

    public GameInfoDto getGame(final String gameId, final String playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
        final List<String> moviesInfo;
        Optional<Game> game = gameRepository.findByPlayer(player);
        GameInfoDto gameInfoDto = GameInfoDto.builder().build();

//        moviesInfo = game.map(value -> value.getMovies().stream().map(Movie::getTitle).collect(Collectors.toList()))
//            .orElseGet(() -> omdbClientExternalService.getRandomMovies().stream().map(MovieDto::getTitle)
//                .collect(Collectors.toList()));

        if (game.isPresent()) {
            GameDto gameDto = GameMapper.INSTANCE.toGameDTO(game.get());
             gameInfoDto = GameMapper.INSTANCE.toGameInfoDTO(gameDto);

        }

        return gameInfoDto;
    }
}
