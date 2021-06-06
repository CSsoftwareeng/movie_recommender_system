package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoviesRepository extends MongoRepository<Movies, String> {

  public Movies findByMovieid(int movieid);
  public List<Movies> findByTitle(String title);
  public List<Movies> findByGenre(String genre);

}