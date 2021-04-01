package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import com.recommend.utils.errors.ArgNotExistError;
import com.recommend.utils.errors.MovieNotExistError;
import com.recommend.utils.errors.UserNotExistError;

public class MainClass {
    public static void main(String[] args) throws ArgCntError {
        if (args.length != 2) {
            throw new ArgCntError(args, args.length);
        }

        Arguments arg = new Arguments(args[0], args[1]);
        MovieList movieList = new MovieList(arg.getGenres());
        UserList userList = new UserList(arg.getOccupation());
        RatingCalculator cal = new RatingCalculator(movieList, userList);

        cal.showResult();
    }
}

