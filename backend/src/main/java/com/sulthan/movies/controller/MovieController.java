package com.sulthan.movies.controller;

import com.sulthan.movies.model.MovieModel;
import com.sulthan.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieModel getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieModel addMovie(@RequestBody MovieModel model) {
        return movieService.addMovie(model);
    }

    @PutMapping("/{id}")
    public MovieModel updateMovie(@PathVariable Long id, @RequestBody MovieModel model) {
        return movieService.updateMovie(id, model);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
    }

    @DeleteMapping
    public void deleteAllMovies() {
        movieService.deleteAllMovies();
    }
}
