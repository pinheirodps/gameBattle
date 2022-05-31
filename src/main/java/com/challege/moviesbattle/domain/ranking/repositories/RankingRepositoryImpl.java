package com.challege.moviesbattle.domain.ranking.repositories;

import com.challege.moviesbattle.domain.game.entities.querydsl.QGame;
import com.challege.moviesbattle.domain.player.entities.querydsl.QPlayer;
import com.challege.moviesbattle.domain.ranking.dto.RankingResponseDto;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Ranking repository.
 */
@Slf4j
@Component
@Transactional()
public class RankingRepositoryImpl extends QuerydslRepositorySupport implements RankingRepository {

    public RankingRepositoryImpl() {
        super(RankingRepositoryImpl.class);
    }

    @Override
    public List<RankingResponseDto> getRanking() {
        QPlayer player = QPlayer.player;
        QGame game = QGame.game;
        final JPQLQuery<RankingResponseDto> query = from(player)
                .innerJoin(game).on(player.userId.eq(game.player.userId))
                .select(Projections.constructor(RankingResponseDto.class, player.username,
                        game.player.userId.count().multiply(player.score)))
                .where(game.filled.eq(Boolean.TRUE))
                .groupBy(GroupBy.list(player.username));
        query.orderBy(player.score.desc());

        return query.fetch();
    }
}
