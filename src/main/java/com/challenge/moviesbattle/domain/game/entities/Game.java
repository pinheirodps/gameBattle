package com.challenge.moviesbattle.domain.game.entities;

import com.challenge.moviesbattle.domain.BaseEntity;
import com.challenge.moviesbattle.domain.game.enums.GameStatus;
import com.challenge.moviesbattle.domain.player.entities.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.Objects;

@Entity
public class Game extends BaseEntity {
    @Column(name = "game_id", nullable = false)
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Getter
    @Setter
    private Integer currentRound;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private Player player;


    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private GameStatus gameStatus;

    @Getter
    @Setter
    private boolean filled;

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
