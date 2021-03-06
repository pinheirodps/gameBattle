package com.challenge.moviesbattle.domain.player.entities;

import com.challenge.moviesbattle.domain.BaseEntity;
import com.challenge.moviesbattle.domain.game.entities.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@RequiredArgsConstructor
public class Player extends BaseEntity {

    public Player(final Long userId, final String username) {
        this.userId = userId;
        this.username = username;
    }

    @Column(name = "user_id", nullable = false)
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;
    @Column(name = "username", nullable = false)
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private int score;

    @Getter
    @Setter
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private Set<Game> games =  new HashSet<>();
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
