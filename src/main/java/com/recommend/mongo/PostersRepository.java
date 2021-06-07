package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface PostersRepository extends MongoRepository<Posters, String> {

  public Posters findByMovieid(int movieid);
  public List<Posters> findByPoster(String poster);
  

  @Query(value = "{'movieid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Posters> findPosterByMovieidIn(List<Integer> movieids);

  @Aggregation("{ '$match': {'movieid': ?0 } }, { '$project': { '_id' : '$poster' } }")
  public String findOneByMovieid(int movieid);

}