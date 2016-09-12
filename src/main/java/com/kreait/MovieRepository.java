package com.kreait;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    @Query("SELECT p FROM Person p")
    List<Person> findAllPeople();

    @Query("SELECT m.id.person FROM MoviePersonRole m WHERE m.id.movie.id = :movieId")
    List<Person> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT m FROM Movie m WHERE m.name LIKE %:name%")
    List<Movie> findMovieByName(@Param("name") String name);
}
