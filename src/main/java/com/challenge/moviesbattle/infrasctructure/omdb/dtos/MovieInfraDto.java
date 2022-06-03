package com.challenge.moviesbattle.infrasctructure.omdb.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieInfraDto implements Serializable {

    private String title;
    private Double ratingValue;
    private Double ratingCount;
}
