package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface PosterRepository extends MongoRepository<Poster, String> {

  public Poster findByMovieid(int movieid);
  public List<Poster> findByPoster(String poster);
  

  @Query(value = "{'movieid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Poster> findPosterByMovieidIn(List<Integer> movieids);

  @Aggregation("{ '$match': {'movieid': ?0 } }, { '$project': { '_id' : '$poster' } }")
  public String findOneByMovieid(int movieid);

}