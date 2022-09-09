package com.neilg.movie_app.service;

import com.neilg.movie_app.data.models.Movie;
import com.neilg.movie_app.data.payloads.request.MovieRequest;
import com.neilg.movie_app.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
This package houses the business logic. This file provides an interface where the methods of our business logic will be declared.
 */
/*
@Component annotation registers the DirectorService interface as a bean in the application context and makes it accessible during classpath scanning.
 */
@Component
public interface MovieService {

    /*
    Allows creation of a new Movie instance
    */
    MessageResponse createMovie(MovieRequest movieRequest);
    /*
    Allows update of an existing Movie instance
    */
    Optional<Movie> updateMovie(Integer movieId, MovieRequest movieRequest);

    /*
    Allows retrieval of a specific Movie with a defined id
    */
    Movie getASingleMovie(Integer movieId);

    /*
    Allows retrieval of all Movie data
    */
    List<Movie> getAllMovie();

    /*
    Allows deletion of a Movie instance using its id
    */
    void deleteMovie(Integer movieId);

    /*
    Allows link of a director instance to a movie using both object ids
    */
    Set<Movie> getDirectorMovies(Integer directorId);

    /*
    Allows retrieval of a movie list of one specific director using the director id as input
    */
    Optional<Movie> addDirectorToMovie(Integer movieId, Integer directorId);
    /*
    Allows retrieval of a movie list within a specific range of rating defined by the input parameters
    */
    public List<Movie> getRatingFilteredMovies(Integer min, Integer max);

    }