package com.challenge.moviesbattle.domain.player.mapper;

import com.challenge.moviesbattle.domain.player.dto.PlayerDto;
import com.challenge.moviesbattle.domain.player.entities.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDto from (Player player);
}
