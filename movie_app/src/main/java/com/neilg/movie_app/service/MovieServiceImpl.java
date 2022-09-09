package com.neilg.movie_app.service;
import com.neilg.movie_app.data.models.Director;
import com.neilg.movie_app.data.models.Movie;
import com.neilg.movie_app.data.payloads.request.MovieRequest;
import com.neilg.movie_app.data.payloads.response.MessageResponse;
import com.neilg.movie_app.data.repository.MovieRepository;
import com.neilg.movie_app.data.repository.DirectorRepository;
import com.neilg.movie_app.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/*
This class implements the service interface.
 */

/*
@Service is specifically a collection of library methods that manage one aspect of an application business logic.
 */
@Service
public class MovieServiceImpl implements MovieService{

/*
The @autowired annotation is used to perform field dependency injection.
*/
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    DirectorRepository directorRepository;

    /*
    Allows creation of a new Movie instance
     */
    @Override
    public MessageResponse createMovie(MovieRequest movieRequest) {
        Movie newMovie = new Movie();
        newMovie.setName(movieRequest.getName());
        newMovie.setRating(movieRequest.getRating());

        movieRepository.save(newMovie);
        return new MessageResponse("New Movie created successfully");

    }

    /*
    Allows update of an existing Movie instance
    */
    @Override
    public Optional<Movie> updateMovie(Integer movieId, MovieRequest movieRequest)  throws ResourceNotFoundException{
        Optional<Movie> movie = movieRepository.findById(movieId);

        if (movie.isPresent()){

            movie.get().setName(movieRequest.getName());
            movie.get().setRating(movieRequest.getRating());
            movieRepository.save(movie.get());

        }
        else
        {
            throw new ResourceNotFoundException("Movie", "id", movieId);
        }

        return movie;
    }
    /*
    Allows retrieval of a specific Movie with a defined id
    */
    @Override
    public Movie getASingleMovie(Integer movieId) throws ResourceNotFoundException{
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
    }

    /*
    Allows retrieval of all Movie data
    */
    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    /*
    Allows deletion of a Movie instance using its id
    */
    @Override
    public void deleteMovie(Integer movieId) throws ResourceNotFoundException {
        if (movieRepository.getById(movieId).getId().equals(movieId)){
            movieRepository.deleteById(movieId);
        }
        else throw new ResourceNotFoundException("Movie", "id", movieId);
    }

    /*
    Allows link of a director instance to a movie using both object ids
    */
    @Override
    public Optional<Movie> addDirectorToMovie(Integer movieId, Integer directorId) {

        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<Director>  director = directorRepository.findById(directorId);

        if (movie.isPresent() && director.isPresent() ){
            movie.get().setMovieDirector(director.get());
            movieRepository.save(movie.get());
        }
        else
        {
            throw new ResourceNotFoundException("Movie", "id", movieId);
        }

        return movie;
    }

    /*
    Allows retrieval of a movie list of one specific director using the director id as input
    */
    @Override
    public Set<Movie>  getDirectorMovies(Integer directorId){
        Optional<Director>  director = directorRepository.findById(directorId);
        return  director.get().getDirectorMovies();
    }

    /*
    Allows retrieval of a movie list within a specific range of rating defined by the input parameters
    */
    @Override
    public List<Movie> getRatingFilteredMovies(Integer min, Integer max){
        List<Movie> full = movieRepository.findAll();
        List<Movie> filtered = new ArrayList<>();

        for(int i = 0; i<full.size(); i++) {


                    if (full.get(i).getRating() > min) {
                        filtered.add(full.get(i));
                    }

            }

        return filtered ;
    }

}
