package com.kreait;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;
    private final FakeService fakeService;

    @Autowired
    public MovieController(MovieRepository movieRepository, FakeService fakeService) {
        this.movieRepository = movieRepository;
        this.fakeService = fakeService;
    }

    @CacheControl(maxage = 30)
    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    public List<MovieDTO> getMovie(@RequestParam(value = "name", required = false) String name) {
        final List<Movie> all;
        if (name != null && !name.isEmpty()) {
            all = movieRepository.findMovieByName(name);
        } else {
            all = movieRepository.findAll();
        }
        return MovieDTO.create(all);
    }

    @RequestMapping(path = "/test")
    public Integer test() {
        return fakeService.returnRandomNumber();
    }

    @RequestMapping(path = "/evict")
    public void evict() {
        fakeService.testEvict();
    }

    @RequestMapping(path = "/movies/{movieId}/people", method = RequestMethod.GET)
    public List<Person> getMoviePeople(@PathVariable(value = "movieId") Long movieId) {
        return movieRepository.findByMovieId(movieId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/movies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MovieDTO addMovie(@RequestBody Movie movie) {
        return new MovieDTO(movieRepository.save(movie));
    }
}
