package com.neilg.movie_app.data.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*

This class describes a Movie which will have the following characteristics:
-an integer ID
-a string name
-an integer rating
This class also has a basic many to many relationship with the 'Director' class

*/

/*
@Entity annotation tells Spring that the Employee class which is simply a POJO (Plain Old Java Object) which should be persisted on the database.
 */
@Entity
public class Movie {

    /*
    @Id annotation makes the id field the primary key of the employee table when it is persisted on the database.
    @GeneratedValue annotation to specify the primary key generation strategy. The value auto instructs the database to automatically generate a value for the field.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer rating;

    /*
    Configuring a Join table and a HashSet which allows linking of both class instances
     */
    @ManyToMany
    @JoinTable(
            name = "director_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private Set<Director> movieDirectors = new HashSet<>();


    /*
        no-argument constructor, setters and getters for our field, and override the toString, equals, and hashCode methods.
     */

    public Movie(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public Integer getRating() {

        return rating;
    }
    public void setRating(Integer inputRating) {
        this.rating = inputRating;
    }

    public Set<Director> getMovieDirectors() {
        return movieDirectors;
    }

    public void setMovieDirector(Director movieDirector) {
        movieDirectors.add(movieDirector);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating='" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(rating, movie.rating) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }

}