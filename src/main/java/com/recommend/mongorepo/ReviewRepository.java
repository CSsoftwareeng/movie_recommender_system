package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;


public interface ReviewRepository extends MongoRepository<Review, String> {

  public List<Review> findByUserid(int userid);
  public List<Review> findByMovieid(int movieid);
  public List<Review> findByRating(int rating);
  public List<Review> findByDate(int date);

}