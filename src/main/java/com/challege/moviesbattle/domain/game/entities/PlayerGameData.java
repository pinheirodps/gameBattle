//package com.challege.moviesbattle.domain.game.entities;
//
//import com.challege.moviesbattle.domain.BaseEntity;
//import com.challege.moviesbattle.domain.player.entities.Player;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "player_games")
//public class PlayerGameData extends BaseEntity {
//
//    @Id
//    @Getter
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Getter
//    @Setter
//    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Player player;
//
//    @Getter
//    @Setter
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "game_id")
//    private Game game;
//
//    @Getter
//    @Setter
//    private int score;
//}
