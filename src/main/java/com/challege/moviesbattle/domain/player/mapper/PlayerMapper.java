package com.challege.moviesbattle.domain.player.mapper;

import com.challege.moviesbattle.domain.player.dto.PlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDto from (Player player);
}
