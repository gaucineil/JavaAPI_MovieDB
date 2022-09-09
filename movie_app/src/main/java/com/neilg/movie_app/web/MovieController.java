package com.neilg.movie_app.web;


import com.neilg.movie_app.data.models.Movie;
import com.neilg.movie_app.data.payloads.request.MovieRequest;
import com.neilg.movie_app.data.payloads.response.MessageResponse;
import com.neilg.movie_app.service.MovieService;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
@RestController: This annotation marks the MovieController as an HTTP request handler and allows Spring to recognize it as a RESTful service.
 */
@RestController
/*
@RequestMapping sets the basepath to the resource endpoints
*/
@RequestMapping("/movie")

/*
Inserted for Swagger API documentation
TO FIX - Whitelabel error page when accessing http://localhost:8081/swagger-ui
*/
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
}
)
public class MovieController {

    @Autowired
    MovieService movieService;

    /*
    HTTP GET request to retrieve all movies
    */
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovie () {
        List<Movie> movieList = movieService.getAllMovie();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }
    /*
    HTTP GET request to retrieve specific movie
    */
    @GetMapping("/find/{id}")
    public ResponseEntity<Movie> getMovieById (@PathVariable("id") Integer id) {
        Movie movie = movieService.getASingleMovie(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    /*
    HTTP POST request to add movie to database
    */
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addMovie( @RequestBody MovieRequest movie) {
        MessageResponse newMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }
    /*
    HTTP PUT request to update existing movie in database
    */
    @PutMapping("/update/{id}")
    public Optional<Movie> updateMovie(@PathVariable Integer id, @RequestBody MovieRequest movie) {
        return movieService.updateMovie(id, movie);

    }
    /*
    HTTP DELETE request to delete existing movie in database
    */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    HTTP PUT request to link an existing Movie to an Existing Director in database
    */
    @PutMapping("/{movieId}/linkdirector/{directorId}")
    public Optional<Movie>  addDirectorToMovie(@PathVariable("movieId") Integer movieId,  @PathVariable("directorId") Integer directorId) {
        return movieService.addDirectorToMovie(movieId, directorId);
    }
    /*
    HTTP GET request to retrieve all movies in database linked to a specific Director
    */
    @GetMapping("/director/{id}")
    public ResponseEntity<Set<Movie>> getDirectorMovies (@PathVariable("id") Integer directorId) {
        Set<Movie> movieList = movieService.getDirectorMovies(directorId);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }
    /*
    HTTP GET request to retrieve all movies in database which are rated within a specific range
    */
    @GetMapping("/min/{minimum}/max/{maximum}")
    public ResponseEntity<List<Movie>> getFilteredRatingMovies (@PathVariable("minimum") Integer min,@PathVariable("maximum") Integer max) {
        return new ResponseEntity<>(movieService.getRatingFilteredMovies(min,max), HttpStatus.OK);
    }


}
