package com.challege.moviesbattle.domain.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDto {

    private String title;
    private String imdbRating;

}
