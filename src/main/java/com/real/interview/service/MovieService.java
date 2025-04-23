package com.real.interview.service;

import com.real.interview.pojo.Movie;
import com.real.interview.repo.MovieRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Transactional
    public Optional<Movie> getMovieByTitle(String title) {
        Optional<com.real.interview.entity.Movie> result = Optional.ofNullable(movieRepo.findByTitle(title));
        Optional<Movie> optionalMovie = Optional.empty();
        if (result.isPresent()) {
            Movie movie = new Movie();
            movie.setTitle(result.get().getTitle());
            movie.setId(result.get().getId());
            movie.setReleaseYear(result.get().getReleaseYear());
            optionalMovie = Optional.of(movie);
        }
        return optionalMovie;
    }


    @Transactional
    public Optional<List<Movie>> getMoviesByReleaseYear(String releaseYear) {
        Optional<List<com.real.interview.entity.Movie>> movies = Optional.ofNullable(movieRepo.findByReleaseYear(releaseYear));
        Optional<List<Movie>> optionalMovie = Optional.empty();
        if (movies.isPresent() && !movies.get().isEmpty()) {
            List<Movie> collect = movies.get().stream().map(movieEntity -> {
                Movie movie = new Movie();
                movie.setId(movieEntity.getId());
                movie.setReleaseYear(movieEntity.getReleaseYear());
                movie.setTitle(movieEntity.getTitle());
                return movie;
            }).toList();
            optionalMovie = Optional.of(collect);
        }
        return optionalMovie;

    }

    @Transactional
    public Movie createMovie(Movie movie) {
        com.real.interview.entity.Movie entity = new com.real.interview.entity.Movie();
        entity.setTitle(movie.getTitle());
        entity.setReleaseYear(movie.getReleaseYear());
        entity = movieRepo.save(entity);
        movie.setId(entity.getId());
        return movie;
    }


    @Transactional
    public Optional<Movie> updateMovie(Movie movie) {

        com.real.interview.entity.Movie movieEntity = new com.real.interview.entity.Movie();
        movieEntity.setId(movie.getId());
        Optional<com.real.interview.entity.Movie> movieObject = movieRepo.findById(movieEntity.getId());

        if (movieObject.isEmpty()) {
            return Optional.empty();
        }
        movieObject.get().setTitle(movie.getTitle());
        movieObject.get().setReleaseYear(movie.getReleaseYear());
        com.real.interview.entity.Movie updateObject = movieRepo.save(movieObject.get());
        movie.setReleaseYear(updateObject.getReleaseYear());
        movie.setTitle(updateObject.getTitle());
        return Optional.of(movie);
    }


    @Transactional
    public Optional<Movie> deleteMovie(Integer movieId) {
        Optional<Movie> optionalMovie = Optional.empty();

        Optional<com.real.interview.entity.Movie> movieEntity = movieRepo.findById(movieId);
        if (movieEntity.isEmpty()) {
            return optionalMovie;
        }
        movieRepo.deleteById(movieId);
        Movie movie = new Movie();
        movie.setId(movieId);
        optionalMovie = Optional.of(movie);
        return optionalMovie;
    }
}
