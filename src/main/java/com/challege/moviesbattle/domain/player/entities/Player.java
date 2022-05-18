package com.challege.moviesbattle.domain.player.entities;

import com.challege.moviesbattle.domain.BaseEntity;
import com.challege.moviesbattle.domain.game.entities.Game;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@RequiredArgsConstructor
public class
Player extends BaseEntity {

    public Player(final String username) {
        this.username = username;
    }

    public Player(final String userId, final String username) {
        this.userId = userId;
        this.username = username;
    }

    @Column(name = "user_id", nullable = false)
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String userId;

    @Column(name = "username", nullable = false)
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Player otherPlayer = (Player) obj;
        return Objects.equals(userId, otherPlayer.userId);
    }

}
