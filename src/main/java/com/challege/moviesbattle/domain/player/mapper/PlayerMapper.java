package com.challege.moviesbattle.domain.player.mapper;

import com.challege.moviesbattle.domain.player.dto.PlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    PlayerDto from (Player player);
}
