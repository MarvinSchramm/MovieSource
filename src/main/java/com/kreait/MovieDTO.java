package com.kreait;


import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDTO {

    private final Movie movie;

    public MovieDTO(Movie movie) {
        this.movie = movie;
    }

    public Long getId() {
        return movie.getId();
    }

    public String getName() {
        return movie.getName();
    }

    public String getDateOfRelease() {
        return movie.getDateOfRelease();
    }

    public static List<MovieDTO> create(List<Movie> movies) {
        Assert.notNull(movies);
        return movies.stream().map(MovieDTO::new).collect(Collectors.toList());
    }
}
