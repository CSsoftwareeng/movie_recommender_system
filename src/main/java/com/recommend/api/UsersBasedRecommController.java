package com.recommend.app;

import com.recommend.app.RatingCalculator;
import com.recommend.utils.errors.*;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UsersBasedRecommController {

  @Autowired
  private MovieList movielist;
  @Autowired
  private UserList userlist;
  @Autowired
  private RatingCalculator rating;

  @GetMapping("/users/recommendations")
  public List<Movies> userBasedAPI(
    @RequestBody Map<String, String> requestBody
  ) {
    try {
      String gender = requestBody.get("gender");
      String age = requestBody.get("age");
      String occupation = requestBody.get("occupation");
      String genres = requestBody.get("genres");

      if (
        requestBody.size() <= 2 || requestBody.size() > 4
      ) throw new ArgCntError((Integer) requestBody.size());

      if (gender == null) throw new ArgMissingError("gender"); else if (
        age == null
      ) throw new ArgMissingError("age"); else if (
        occupation == null
      ) throw new ArgMissingError("occupation");

      Arguments arg;
      if (genres == null) {
        if (requestBody.size() == 4) throw new WrongArgError("user");
        arg = new Arguments(gender, age, occupation);
      } else arg = new Arguments(gender, age, occupation, genres);

      movielist.searchID(arg.getGenres());
      userlist.searchSimilarUser(
        arg.getGender(),
        arg.getAge(),
        arg.getOccupation()
      );
      rating.setLists(movielist, userlist);
      rating.rankUserBasedRating(10);
      rating.calcResult();
      return rating.getMoviesResult();
    } catch (MovieNotExistError e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (ArgNotExistError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (ArgCntError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (ArgMissingError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (WrongArgError e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
