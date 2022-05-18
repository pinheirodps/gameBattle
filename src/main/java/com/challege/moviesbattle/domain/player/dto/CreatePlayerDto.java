package com.challege.moviesbattle.domain.player.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerDto {

    private String username;
    private String movieName;

}
