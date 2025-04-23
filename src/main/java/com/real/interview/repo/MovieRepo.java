package com.real.interview.repo;

import com.real.interview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    Movie findByTitle(String title);

    List<Movie> findByReleaseYear(String releaseYear);
}
