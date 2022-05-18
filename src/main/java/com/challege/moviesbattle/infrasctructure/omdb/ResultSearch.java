package com.challege.moviesbattle.infrasctructure.omdb;

import com.challege.moviesbattle.infrasctructure.omdb.dtos.MovieInfraDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ResultSearch {


    private Integer total;

    @JsonProperty("Title")
    private String title;
    @JsonProperty("imdbRating")
    private String imdbRating;
    private Boolean response;

    @JsonProperty("Response")
    public void setResponse(String response) {
        this.response = Boolean.valueOf(response);
    }

    @JsonProperty("totalResults")
    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }

}
