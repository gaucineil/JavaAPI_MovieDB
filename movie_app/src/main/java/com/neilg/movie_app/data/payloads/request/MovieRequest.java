package com.neilg.movie_app.data.payloads.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MovieRequest {

/*
@NotBlank and @NotNull: These annotations check and validate that the fields they are mapped to are not blank and null.
*/
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private Integer rating;


    public String getName() {
        return name;
    }

    public void setName(String nameSet) {
        this.name = nameSet;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer ratingSet) {
        this.rating = ratingSet;
    }

}
