package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<Link, String> {

  public Link findByMovieid(int movieid);
  public List<Link> findByLink(int link);

}