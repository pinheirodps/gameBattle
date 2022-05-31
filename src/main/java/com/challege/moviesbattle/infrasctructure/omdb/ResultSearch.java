package com.challege.moviesbattle.infrasctructure.omdb;

import com.challege.moviesbattle.infrasctructure.omdb.dtos.MovieInfraDto;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResultSearch {

    private final List<MovieInfraDto> movies;

}
