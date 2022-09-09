package com.neilg.movie_app.data.repository;
import com.neilg.movie_app.data.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
@Repository annotation makes the interface a bean.
By extending the JpaRepository interface we inherit the save, findAll, findById methods.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
