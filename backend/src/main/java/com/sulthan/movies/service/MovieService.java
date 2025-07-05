package com.sulthan.movies.service;

import com.sulthan.movies.entity.Movie;
import com.sulthan.movies.model.MovieModel;
import com.sulthan.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepo;

    // Convert Entity -> DTO
    private MovieModel convertToModel(Movie movie) {
        return new MovieModel(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getRating()
        );
    }

    // Convert DTO -> Entity
    private Movie convertToEntity(MovieModel model) {
        Movie movie = new Movie();
        movie.setId(model.getId());
        movie.setTitle(model.getTitle());
        movie.setGenre(model.getGenre());
        movie.setRating(model.getRating());
        return movie;
    }

    public List<MovieModel> getAllMovies() {
        return movieRepo.findAll()
                .stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public MovieModel getMovieById(Long id) {
        Optional<Movie> movie = movieRepo.findById(id);
        return movie.map(this::convertToModel).orElse(null);
    }

    public MovieModel addMovie(MovieModel model) {
        Movie movie = convertToEntity(model);
        return convertToModel(movieRepo.save(movie));
    }

    public MovieModel updateMovie(Long id, MovieModel model) {
        Movie movie = convertToEntity(model);
        movie.setId(id);
        return convertToModel(movieRepo.save(movie));
    }

    public void deleteMovieById(Long id) {
        movieRepo.deleteById(id);
    }

    public void deleteAllMovies() {
        movieRepo.deleteAll();
    }
}
