package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinksRepository extends MongoRepository<Links, String> {

  public Links findByMovieid(int movieid);

}