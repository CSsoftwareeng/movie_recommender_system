package com.recommend.app;

import com.recommend.utils.errors.ArgNotExistError;
import java.util.*;

public class Arguments {

  String gender;
  String age;
  String occupation;
  List<String> genres = new ArrayList<String>();

  String raw_occupation = null;
  String raw_genres = null;
  public String err_sender_str = null;

  static final Map<String, String> GENRE_MAP = new HashMap<String, String>();
  static final Map<String, Integer> OCCUPATIONS_MAP = new HashMap<String, Integer>();

  public Arguments() {}

  public Arguments(String arg1, String arg2, String arg3) {
    setMap();
    setArgs(arg1, arg2, arg3);
    parseGender();
    parseAge();
    parseOccupation();
  }

  public Arguments(String arg1, String arg2, String arg3, String arg4) {
    setMap();
    setArgs(arg1, arg2, arg3, arg4);
    parseGender();
    parseAge();
    parseOccupation();
    parseGenres();
  }

  void setMap() {
    OCCUPATIONS_MAP.put("other", 0);
    OCCUPATIONS_MAP.put("academic/educator", 1);
    OCCUPATIONS_MAP.put("academiceducator", 1);
    OCCUPATIONS_MAP.put("academic", 1);
    OCCUPATIONS_MAP.put("educator", 1);
    OCCUPATIONS_MAP.put("artist", 2);
    OCCUPATIONS_MAP.put("clerical/admin", 3);
    OCCUPATIONS_MAP.put("clericaladmin", 3);
    OCCUPATIONS_MAP.put("clerical", 3);
    OCCUPATIONS_MAP.put("admin", 3);
    OCCUPATIONS_MAP.put("college/gradstudent", 4);
    OCCUPATIONS_MAP.put("collegegradstudent", 4);
    OCCUPATIONS_MAP.put("collegestudent", 4);
    OCCUPATIONS_MAP.put("gradstudent", 4);
    OCCUPATIONS_MAP.put("customerservice", 5);
    OCCUPATIONS_MAP.put("doctor/healthcare", 6);
    OCCUPATIONS_MAP.put("doctorhealthcare", 6);
    OCCUPATIONS_MAP.put("doctor", 6);
    OCCUPATIONS_MAP.put("healthcare", 6);
    OCCUPATIONS_MAP.put("executive/managerial", 7);
    OCCUPATIONS_MAP.put("executivemanagerial", 7);
    OCCUPATIONS_MAP.put("executive", 7);
    OCCUPATIONS_MAP.put("managerial", 7);
    OCCUPATIONS_MAP.put("farmer", 8);
    OCCUPATIONS_MAP.put("homemaker", 9);
    OCCUPATIONS_MAP.put("k-12student", 10);
    OCCUPATIONS_MAP.put("k12student", 10);
    OCCUPATIONS_MAP.put("lawyer", 11);
    OCCUPATIONS_MAP.put("programmer", 12);
    OCCUPATIONS_MAP.put("retired", 13);
    OCCUPATIONS_MAP.put("sales/marketing", 14);
    OCCUPATIONS_MAP.put("salesmarketing", 14);
    OCCUPATIONS_MAP.put("sales", 14);
    OCCUPATIONS_MAP.put("marketing", 14);
    OCCUPATIONS_MAP.put("scientist", 15);
    OCCUPATIONS_MAP.put("self-employed", 16);
    OCCUPATIONS_MAP.put("selfemployed", 16);
    OCCUPATIONS_MAP.put("technician/engineer", 17);
    OCCUPATIONS_MAP.put("technicianengineer", 17);
    OCCUPATIONS_MAP.put("technician", 17);
    OCCUPATIONS_MAP.put("engineer", 17);
    OCCUPATIONS_MAP.put("tradesman/craftsman", 18);
    OCCUPATIONS_MAP.put("tradesmancraftsman", 18);
    OCCUPATIONS_MAP.put("tradesman", 18);
    OCCUPATIONS_MAP.put("craftsman", 18);
    OCCUPATIONS_MAP.put("unemployed", 19);
    OCCUPATIONS_MAP.put("writer", 20);

    GENRE_MAP.put("action", "Action");
    GENRE_MAP.put("adventure", "Adventure");
    GENRE_MAP.put("animation", "Animation");
    GENRE_MAP.put("children's", "Children's");
    GENRE_MAP.put("comedy", "Comedy");
    GENRE_MAP.put("crime", "Crime");
    GENRE_MAP.put("documentary", "Documentary");
    GENRE_MAP.put("drama", "Drama");
    GENRE_MAP.put("fantasy", "Fantasy");
    GENRE_MAP.put("film-noir", "Film-Noir");
    GENRE_MAP.put("filmnoir", "Film-Noir");
    GENRE_MAP.put("horror", "Horror");
    GENRE_MAP.put("musical", "Musical");
    GENRE_MAP.put("mystery", "Mystery");
    GENRE_MAP.put("romance", "Romance");
    GENRE_MAP.put("sci-fi", "Sci-Fi");
    GENRE_MAP.put("scifi", "Sci-Fi");
    GENRE_MAP.put("thriller", "Thriller");
    GENRE_MAP.put("war", "War");
    GENRE_MAP.put("western", "Western");
  }

  void setArgs(String arg1, String arg2, String arg3) {
    this.gender = arg1;
    this.age = arg2;
    this.raw_occupation = arg3;
  }

  void setArgs(String arg1, String arg2, String arg3, String arg4) {
    this.gender = arg1;
    this.age = arg2;
    this.raw_occupation = arg3;
    this.raw_genres = arg4;
  }

  void parseGender() throws ArgNotExistError {
    if (
      this.gender.toUpperCase().equals("F") ||
      this.gender.toUpperCase().equals("M") ||
      this.gender.toUpperCase().equals("")
    ) this.gender = this.gender.toUpperCase(); else throw new ArgNotExistError(
      this,
      1
    );
  }

  void parseAge() throws ArgNotExistError {
    if (this.age.equals("")) return; else {
      int ageToNum = Integer.parseInt(this.age);
      if (ageToNum >= 0 && ageToNum < 18) this.age = "1"; else if (
        ageToNum >= 18 && ageToNum <= 24
      ) this.age = "18"; else if (ageToNum >= 25 && ageToNum <= 34) this.age =
        "25"; else if (ageToNum >= 35 && ageToNum <= 44) this.age =
        "35"; else if (ageToNum >= 45 && ageToNum <= 49) this.age =
        "45"; else if (ageToNum >= 50 && ageToNum <= 55) this.age =
        "50"; else if (ageToNum >= 56) this.age =
        "56"; else throw new ArgNotExistError(this, 2);
    }
  }

  void parseOccupation() throws ArgNotExistError {
    String st1 = raw_occupation;
    if (st1.equals("")) {
      this.occupation = "";
    } else {
      st1 = st1.trim().toLowerCase().replaceAll("\\p{Z}", "");

      if (!OCCUPATIONS_MAP.containsKey(st1)) {
        this.err_sender_str = st1;
        throw new ArgNotExistError(this, 3);
      } else {
        this.occupation =
          Integer.toString(
            OCCUPATIONS_MAP.get(
              st1.trim().toLowerCase().replaceAll("\\p{Z}", "")
            )
          );
      }
    }
  }

  void parseGenres() throws ArgNotExistError {
    String st1 = raw_genres;

    if (st1.equals("")) {
      return;
    } else {
      StringTokenizer gen_st = new StringTokenizer(st1, "|");
      while (gen_st.hasMoreTokens()) {
        this.genres.add(
            gen_st.nextToken().trim().toLowerCase().replaceAll("\\p{Z}", "")
          );
      }
      for (int i = 0; i < genres.size(); i++) {
        String genre = genres.get(i);
        if (!GENRE_MAP.containsKey(genre)) {
          this.err_sender_str = genre;
          throw new ArgNotExistError(this, 4);
        } else {
          genres.set(i, GENRE_MAP.get(genre));
        }
      }
    }
  }

  void printArgs() {
    System.out.println("*** arg 1 : " + this.gender + " ***");
    System.out.println("*** arg 2 : " + this.age + " ***");
    System.out.println("*** arg 3 : " + this.occupation + " ***");
    System.out.println("*** arg 4 : " + this.genres + " ***");
  }

  public String getGender() {
    return this.gender;
  }

  public String getAge() {
    return this.age;
  }

  public String getOccupation() {
    return this.occupation;
  }

  public List<String> getGenres() {
    return this.genres;
  }

  public String getRawOccupation() {
    return this.raw_occupation;
  }

  public String getRawGenre() {
    return this.raw_genres;
  }
}
