package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

public interface UsersRepository extends MongoRepository<Users, String> {

  public Users findByUserid(int userid);
  public List<Users> findByGender(String gender);
  public List<Users> findByAge(int age);
  public List<Users> findByOccupation(int occupation);
  @Aggregation("{ '$match': {'occupation': ?0 } }, { '$project': { '_id' : '$userid' } }")
  public List<Integer> findUseridByOccupation(int occupation);
  public List<Users> findByZipcode(int zipcode);
}