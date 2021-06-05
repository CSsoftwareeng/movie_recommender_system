package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String> {

  public List<Rating> findByUserid(int userid);
  public List<Rating> findByMovieid(int movieid);
  public List<Rating> findByRating(int rating);
  public List<Rating> findByDate(int date);

}