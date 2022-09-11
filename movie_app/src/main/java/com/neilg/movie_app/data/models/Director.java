package com.neilg.movie_app.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*

This class describes a Director which will has the following characteristics:
-an integer ID
-a string name
This class also has a basic many to many relationship with the 'Movie' class

*/

/*
@Entity annotation tells Spring that the Director class which is simply a POJO (Plain Old Java Object) which should be persisted on the database.
 */
@Entity
public class Director {

    /*
    @Id annotation makes the id field the primary key of the director table when it is persisted on the database.
    @GeneratedValue annotation to specify the primary key generation strategy. The value auto instructs the database to automatically generate a value for the field.
     */
    @Id //
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    /*
    Referencing to mapping with Join table generated in Movie class and a generation of HashSet which allows linking of both class instances
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "movieDirectors")
    private Set<Movie> directorMovies = new HashSet<>();

    /*
    no-argument constructor, setters and getters for our field, and override the toString, equals, and hashCode methods.
    */
    public Director(){}

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

    public Set<Movie> getDirectorMovies() {
        return directorMovies;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(id, director.id) && Objects.equals(name, director.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
