package com.neilg.movie_app.service;
import com.neilg.movie_app.data.models.Director;
import com.neilg.movie_app.data.payloads.request.DirectorRequest;
import com.neilg.movie_app.data.payloads.response.MessageResponse;
import com.neilg.movie_app.data.repository.DirectorRepository;
import com.neilg.movie_app.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
This class implements the service interface.
 */
/*
@Service is specifically a collection of library methods that manage one aspect of an application business logic.
 */
@Service
public class DirectorServiceImpl implements DirectorService{

/*
The @autowired annotation is used to perform field dependency injection.
*/
    @Autowired
    DirectorRepository directorRepository;

    /*
    Allows creation of a new Director instance
    */
    @Override
    public MessageResponse createDirector(DirectorRequest directorRequest) {
        Director newDirector = new Director();
        newDirector.setName(directorRequest.getName());

        directorRepository.save(newDirector);
        return new MessageResponse("New Director created successfully");

    }

    /*
    Allows update of an existing Director instance
    */
    @Override
    public Optional<Director> updateDirector(Integer directorId, DirectorRequest directorRequest)  throws ResourceNotFoundException{
        Optional<Director> director = directorRepository.findById(directorId);

        if (director.isPresent()){

            director.get().setName(directorRequest.getName());
            directorRepository.save(director.get());

        }
        else
        {
            throw new ResourceNotFoundException("Director", "id", directorId);
        }

        return director;
    }
    /*
    Allows retrieval of a specific Director with a defined id
    */
    @Override
    public Director getASingleDirector(Integer directorId) throws ResourceNotFoundException{
        return directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director", "id", directorId));
    }

    /*
    Allows retrieval of all Director data
    */
    @Override
    public List<Director> getAllDirector() {
        return directorRepository.findAll();
    }

    /*
    Allows deletion of a Director instance using its id
    */
    @Override
    public void deleteDirector(Integer directorId) throws ResourceNotFoundException {
        if (directorRepository.getById(directorId).getId().equals(directorId)){
            directorRepository.deleteById(directorId);
        }
        else throw new ResourceNotFoundException("Director", "id", directorId);
    }


}
