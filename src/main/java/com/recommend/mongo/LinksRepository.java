package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface LinksRepository extends MongoRepository<Links, String> {

  public Links findByMovieid(int movieid);

  @Query(value = "{'movieid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Links> findLinkByMovieidIn(List<Integer> movieids);
}