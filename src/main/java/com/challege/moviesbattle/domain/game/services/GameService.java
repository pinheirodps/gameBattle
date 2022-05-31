package com.challege.moviesbattle.domain.game.services;

import com.challege.moviesbattle.domain.game.dto.CreateGameDto;
import com.challege.moviesbattle.domain.game.dto.GameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.dto.GameOverDto;
import com.challege.moviesbattle.domain.game.dto.GameResponseDto;
import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.game.entities.Movie;
import com.challege.moviesbattle.domain.game.enums.GameStatus;
import com.challege.moviesbattle.domain.game.exceptions.GameNotFoundException;
import com.challege.moviesbattle.domain.game.exceptions.GameOverException;
import com.challege.moviesbattle.domain.game.mapper.GameMapper;
import com.challege.moviesbattle.domain.game.mapper.MovieMapper;
import com.challege.moviesbattle.domain.game.repositories.GameRepository;
import com.challege.moviesbattle.domain.movie.exceptions.MovieNotFoundException;
import com.challege.moviesbattle.domain.movie.repositories.MovieRepository;
import com.challege.moviesbattle.domain.movie.services.MovieService;
import com.challege.moviesbattle.domain.player.dto.PlayDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import com.challege.moviesbattle.domain.player.exceptions.PlayerNotFoundException;
import com.challege.moviesbattle.domain.player.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Game service.
 */
@Service
@RequiredArgsConstructor
public class GameService {

    /**
     * The constant ONE.
     */
    public static final int ONE = 1;
    /**
     * The constant THREE.
     */
    public static final int THREE = 3;
    private final MovieService movieService;
    private final GameRepository gameRepository;
    private final MovieRepository movieRepository;
    private final GameMapper gameMapper;
    private final MovieMapper movieMapper;
    private final PlayerRepository playerRepository;

    private int page;

    /**
     * Create game and associated players.
     *
     * @param createGameDto the creation game dto
     * @return the game response dto
     */
    @Transactional
    public GameResponseDto createGame(CreateGameDto createGameDto) {
        Player player = getPlayer(createGameDto.getUsername());
        Game gameToSave = new Game();
        gameToSave.setGameStatus(GameStatus.OPEN);
        gameToSave.setPlayer(player);
        saveMoviesDao(movieService.findMoviesPaginate(++page));
        return gameMapper.toResponseDto(gameRepository.save(gameToSave));
    }

    /**
     * Play game.
     *
     * @param gameId  the game id
     * @param playDto the play dto
     * @return the game info dto
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public GameInfoDto play(final Long gameId, final PlayDto playDto) {
        Game game = getGameOrElseThrow(gameId);
        validateGame(game, Optional.ofNullable(playDto.getMovieTitle()));
        Player player = getPlayer(playDto.getUsername());

        game.setGameStatus(GameStatus.PLAYING);
        game.setFilled(Boolean.TRUE);
        Movie movie = getMovies().stream().max(Comparator.comparing(Movie::getBest))
                .orElseThrow(NoSuchElementException::new);

        if (playDto.getMovieTitle().equalsIgnoreCase(movie.getTitle())) {
            player.setScore(player.getScore() + ONE);
            playerRepository.save(player);
            saveMoviesDao(movieService.findMoviesPaginate(++page));
        } else {
            game.setCurrentRound(game.getCurrentRound() + ONE);
            saveMoviesDao(movieService.findMoviesPaginate(++page));
        }
        if (game.getCurrentRound() > THREE) {
            game.setGameStatus(GameStatus.ENDED);
        }
        gameRepository.save(game);

        return getGame(gameId);
    }


    /**
     * Gets game.
     *
     * @param gameId the game id
     * @return the game
     */
    public GameInfoDto getGame(final Long gameId) {
        GameInfoDto gameInfoDto = GameInfoDto.builder().build();

        final Game game = getGameOrElseThrow(gameId);
        validateGame(game, Optional.empty());

        if (!getMovies().isEmpty()) {
            final GameDto gameDto = gameMapper.toGameDTO(game);
            Set<MovieDto> movieDtos = movieMapper.toMoviesDto(getMovies());
            gameDto.setMovies(movieDtos);
            gameInfoDto = gameMapper.toGameInfoDTO(gameDto);
        }

        return gameInfoDto;
    }

    /**
     * Game over, game was finished.
     *
     * @param gameId the game id
     * @return the game over dto
     */
    @Transactional()
    public GameOverDto gameOver(final Long gameId) {
        Game game = getGameOrElseThrow(gameId);
        game.setGameStatus(GameStatus.ENDED);
        return gameMapper.toGameOverDto(gameRepository.save(game));
    }

    private void validateGame(final Game game, final Optional<String> movieTitle) {
        if (game.getGameStatus() == GameStatus.ENDED) {
            throw new GameOverException(game.getId());
        }
        movieTitle.ifPresent(movie -> movieRepository.findMovieByTitle(movie)
                .orElseThrow(() -> new MovieNotFoundException(movie)));
    }

    private Game getGameOrElseThrow(final Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }

    private void saveMoviesDao(final List<MovieDto> movies) {
        Set<Movie> moviesDao = movies.stream()
                .map(movieDto -> new Movie(movieDto.getTitle(), movieDto.getRatingValue(),
                        movieDto.getRatingCount()))
                .collect(Collectors.toSet());
        movieRepository.saveAll(moviesDao);
    }

    private Player getPlayer(final String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new PlayerNotFoundException(username));
    }

    private Set<Movie> getMovies() {
        return new HashSet<>(movieRepository.findAll());
    }

}
