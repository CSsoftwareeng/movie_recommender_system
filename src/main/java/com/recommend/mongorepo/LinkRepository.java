package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface LinkRepository extends MongoRepository<Link, String> {

  public Link findByMovieid(int movieid);

  @Query(value = "{'movieid': {'$in' : ?0 } }", fields = "{'_id': 0}")
  List<Link> findLinkByMovieidIn(List<Integer> movieids);
}