package com.recommend.app;

import com.recommend.app.Movies;
import com.recommend.utils.errors.*;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MoviesController {

    @GetMapping("/movies")
    public List<Movie> moviesAPI(
    ) {
        Movies movies = new Movies();
        movies.searchAllMovies();
        return movies.getMoviesResult();
    }
}
