package com.recommend.app;

import com.recommend.utils.errors.*;

public class MainClass {

  public static void main(String[] args) {
    try {
      if (args.length <= 2 || args.length > 4) {
        throw new ArgCntError(args.length);
      }

      Arguments arg = args.length == 3
        ? new Arguments(args[0], args[1], args[2])
        : new Arguments(args[0], args[1], args[2], args[3]);

      MovieList movieList = new MovieList(arg.getGenres());
      UserList userlist = new UserList();
      userlist.searchSimilarUser(
        arg.getGender(),
        arg.getAge(),
        arg.getOccupation()
      );
      RatingCalculator rating = new RatingCalculator(movieList, userlist);
      rating.showResult(movieList);
    } catch (MovieNotExistError e) {} catch (ArgNotExistError e) {} catch (
      ArgCntError e
    ) {}
  }
}
