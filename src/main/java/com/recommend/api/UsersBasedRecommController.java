package com.recommend.app;

import com.recommend.app.RatingCalculator;
import com.recommend.utils.errors.*;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersBasedRecommController {

  @GetMapping("/users/recommendations")
  public List<Movie> userBasedAPI(
    @RequestBody Map<String, String> requestParams
  ) {
    try {
      String gender = requestParams.get("gender");
      String age = requestParams.get("age");
      String occupation = requestParams.get("occupation");
      String genre = requestParams.get("genre");
      Arguments arg = new Arguments(gender, age, occupation, genre);
      MovieList movieList = new MovieList(arg.getGenres());
      UserList userlist = new UserList();
      userlist.searchSimilarUser(
        arg.getGender(),
        arg.getAge(),
        arg.getOccupation()
      );
      RatingCalculator rating = new RatingCalculator(movieList, userlist);
      rating.calcResult(movieList);
      return rating.getMoviesResult();
    } catch (MovieNotExistError e) {} catch (ArgNotExistError e) {} catch (
      ArgCntError e
    ) {}
    return null;
  }
}
