package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {

  public Movie findByMovieid(int movieid);
  public List<Movie> findByTitle(String title);
  public List<Movie> findByGenre(String genre);

}