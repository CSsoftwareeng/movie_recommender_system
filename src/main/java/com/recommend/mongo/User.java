package com.recommend.app;

import org.springframework.data.annotation.Id;

public class User {

  @Id
  public String id;

  public int userid;
  public String gender;
  public int age;
  public int occupation;
  public int zipcode;

  public User() {}

  public User(int movieid, Sting gender, int age, int occupation, int zipcode) {
    this.userid = userid;
    this.gender = gender;
    this.age = age;
    this.occupation = occupation;
    this.zipcode = zipcode;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%s, userid='%d', gender='%d', age='%d', occupation='%d', zipcode='%d']",
        id, userid, gender, age, occupation, zipcode);
  }
}
