package com.neilg.movie_app.data.payloads.request;

import com.neilg.movie_app.data.models.Movie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class DirectorRequest {

/*
@NotBlank and @NotNull: These annotations check and validate that the fields they are mapped to are not blank and null.
*/
    @NotBlank
    @NotNull
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String nameSet) {
        this.name = nameSet;
    }

}
