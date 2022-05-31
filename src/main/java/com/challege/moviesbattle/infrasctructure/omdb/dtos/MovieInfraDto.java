package com.challege.moviesbattle.infrasctructure.omdb.dtos;

import lombok.*;

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
