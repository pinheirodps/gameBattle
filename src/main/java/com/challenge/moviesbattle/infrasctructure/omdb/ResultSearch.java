package com.challenge.moviesbattle.infrasctructure.omdb;

import com.challenge.moviesbattle.infrasctructure.omdb.dtos.MovieInfraDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResultSearch {

    private final List<MovieInfraDto> movies;

}
