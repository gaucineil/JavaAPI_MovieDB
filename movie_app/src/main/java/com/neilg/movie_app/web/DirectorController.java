package com.neilg.movie_app.web;


import com.neilg.movie_app.data.models.Director;
import com.neilg.movie_app.data.models.Movie;
import com.neilg.movie_app.data.payloads.request.DirectorRequest;
import com.neilg.movie_app.data.payloads.response.MessageResponse;
import com.neilg.movie_app.service.DirectorService;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
@RestController: This annotation marks the DirectorController as an HTTP request handler and allows Spring to recognize it as a RESTful service.
 */
@RestController
/*
@RequestMapping sets the base path to the resource endpoints
*/
@RequestMapping("/director")

public class DirectorController {

    @Autowired
    DirectorService directorService;
    /*
    HTTP GET request to retrieve all Directors
    */
    @GetMapping("/all")
    public ResponseEntity<List<Director>> getAllMovie () {
        List<Director> movieList = directorService.getAllDirector();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    /*
    HTTP GET request to retrieve specific director
    */
    @GetMapping("/find/{id}")
    public ResponseEntity<Director> getMovieById (@PathVariable("id") Integer id) {
        Director director = directorService.getASingleDirector(id);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    /*
    HTTP POST request to add director to database
    */
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addDirector( @RequestBody DirectorRequest director) {
        MessageResponse newDirector = directorService.createDirector(director);
        return new ResponseEntity<>(newDirector, HttpStatus.CREATED);
    }

    /*
    HTTP PUT request to update existing director in database
    */
    @PutMapping("/update/{id}")
    public Optional<Director> updateMovie(@PathVariable Integer id, @RequestBody DirectorRequest director) {
        return directorService.updateDirector(id, director);

    }
    /*
    HTTP DELETE request to delete existing director in database
    */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable("id") Integer id) {
        directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
