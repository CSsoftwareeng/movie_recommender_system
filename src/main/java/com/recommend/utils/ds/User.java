package com.recommend.app;

import org.springframework.data.annotation.Id;

public class User {

  @Id
  public String id;

  public int userid;
  public String gender;
  public int age;
  public int occupation;
  public String zipcode;

  public User() {}

  public User(int movieid, String gender, int age, int occupation, String zipcode) {
    this.userid = userid;
    this.gender = gender;
    this.age = age;
    this.occupation = occupation;
    this.zipcode = zipcode;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%s, userid='%d', gender='%d', age='%d', occupation='%d', zipcode='%s']",
        id, userid, gender, age, occupation, zipcode);
  }
}
