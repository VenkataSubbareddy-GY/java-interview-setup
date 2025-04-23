package com.real.interview.controller;

import com.real.interview.pojo.Movie;
import com.real.interview.pojo.ResponseObject;
import com.real.interview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/greet")
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello...", HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<ResponseObject<Movie>> getMovieByTitle(@PathVariable String title) {

        Optional<Movie> result = movieService.getMovieByTitle(title);
        ResponseObject<Movie> responseObject = new ResponseObject<>();
        if (result.isPresent()) {
            responseObject.setObj(result.get());
        } else {
            responseObject.setStatusMessage("No Movie exist(s) with the title : " + title);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<ResponseObject<List<Movie>>> getMoviesByReleaseYear(@PathVariable String year) {
        Optional<List<Movie>> result = movieService.getMoviesByReleaseYear(year);
        ResponseObject<List<Movie>> responseObject = new ResponseObject<>();
        if (result.isPresent() && !result.get().isEmpty()) {
            responseObject.setObj(result.get());
        } else {
            responseObject.setStatusMessage("No Movie released in the year : " + year);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.OK);
    }

    @PutMapping("/movie")
    public ResponseEntity<ResponseObject<Optional<Movie>>> updateMovie(@RequestBody Movie movie) {
        ResponseObject<Optional<Movie>> responseObject = new ResponseObject<>();
        if (movie == null || movie.getId() == null) {
            responseObject.setStatusMessage("Invalid movie id : " + movie.getId());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        Optional<Movie> movieResult = movieService.updateMovie(movie);
        if (movieResult.isEmpty()) {
            responseObject.setStatusMessage("Invalid movie id : " + movie.getId());
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }
        responseObject.setObj(movieResult);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<ResponseObject<Optional<Movie>>> deleteMovie(@PathVariable Integer id) {
        ResponseObject<Optional<Movie>> responseObject = new ResponseObject<>();
        Optional<Movie> movie = movieService.deleteMovie(id);
        if (movie.isEmpty()) {
            responseObject.setStatusMessage("No Movie exists with id : " + id);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }
        responseObject.setStatusMessage("Movie deleted successfully with id : " + id);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
