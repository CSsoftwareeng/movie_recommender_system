package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface CacheRepository extends MongoRepository<Cache, String> {

  @Aggregation({"{ '$match': {'type': ?0 } }", "{'$sort' : { 'rank' : 1 } }" ,"{ '$project': { '_id' : 0, 'type': 0, 'rank': 0 } }"})
  List<Movies> findByType(String type);
}