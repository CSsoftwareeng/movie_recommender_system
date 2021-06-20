package com.recommend.app;

import java.util.List;
import java.util.TreeSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;


public interface ReviewRepository extends MongoRepository<Review, String> {

  public List<Review> findByUserid(int userid); //sort 필요.
  public List<Review> findByMovieidOrderByUseridAsc(int movieid);
  public List<Review> findByRating(int rating);
  public List<Review> findByDate(int date);

  @Aggregation({"{ '$match': { 'userid': {'$in': ?0 } } }", "{ '$group': { '_id': '$userid', 'avgRating': { '$avg': '$rating' } } }", "{ '$sort': { '_id': 1 } }", "{ '$project': { '_id': '$avgRating' } }"})
  List<Double> findAverageRatingByUserid(List<Integer> users);
  @Query(value = "{'userid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Review> findByUseridIn(TreeSet<Integer> users);

}