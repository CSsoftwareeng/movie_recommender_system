package com.recommend.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {

  public Users findByUserid(int userid);
  public List<Users> findByGender(String gender);
  public List<Users> findByAge(int age);
  public List<Users> findByOccupation(int occupation);
  public List<Users> findByZipcode(int zipcode);
}