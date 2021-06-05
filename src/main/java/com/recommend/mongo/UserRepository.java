package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  public User findByUserid(int userid);
  public List<User> findByGender(String gender);
  public List<User> findByAge(int age);
  public List<User> findByOccupation(int occupation);
  public List<User> findByZipcode(int zipcode);
}