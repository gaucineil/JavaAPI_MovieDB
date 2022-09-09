package com.neilg.movie_app.service;

import com.neilg.movie_app.data.models.Director;
import com.neilg.movie_app.data.payloads.request.DirectorRequest;
import com.neilg.movie_app.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/*
This package houses the business logic. This file provides an interface where the methods of our business logic will be declared.
 */

/*
@Component annotation registers the DirectorService interface as a bean in the application context and makes it accessible during classpath scanning.
 */
@Component
public interface DirectorService {
    /*
    Allows creation of a new Director instance
    */
    MessageResponse createDirector(DirectorRequest directorRequest);
    /*
    Allows update of an existing Director instance
    */
    Optional<Director> updateDirector(Integer directorId, DirectorRequest directorRequest);
    /*
    Allows retrieval of a specific Director with a defined id
    */
    Director getASingleDirector(Integer directorId);
    /*
    Allows retrieval of all Director data
    */
    List<Director> getAllDirector();
    /*
    Allows deletion of a Director instance using its id
    */
    void deleteDirector(Integer directorId);

}