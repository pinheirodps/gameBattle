package com.challege.moviesbattle.domain.game.entities;

import com.challege.moviesbattle.domain.BaseEntity;
import com.challege.moviesbattle.domain.game.enums.GameStatus;
import com.challege.moviesbattle.domain.player.entities.Player;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Game extends BaseEntity {


    @Column(name = "game_id", nullable = false)
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private Integer currentRound;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Set<PlayerGameData> playerGameData = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private Player player;


    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.currentRound = 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentRound);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Game otherGame = (Game) obj;
        return Objects.equals(id, otherGame.id) && Objects.equals(currentRound, otherGame.currentRound);
    }


}
