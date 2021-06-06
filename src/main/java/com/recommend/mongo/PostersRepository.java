package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostersRepository extends MongoRepository<Posters, String> {

  public Posters findByMovieid(int movieid);
  public List<Posters> findByPoster(String poster);

}