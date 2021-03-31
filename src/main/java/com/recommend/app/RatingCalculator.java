package com.recommend.app;

import java.io.*;
import java.util.*;

public class RatingCalculator {
    int sum = 0;
    int count = 0;
    int average = 0;

    public void calcAverage (MovieList movies, UserList users) throws IOException {
        File usersFile = new File("/root/project/movie_recommender_system/data/users.dat");
        FileReader reader = new FileReader(usersFile);
        BufferedReader buffer = new BufferedReader(reader);
        String line = "";
        while((line = buffer.readLine()) != null){
            String[] rating = line.split("::");
            if(users.find(Integer.parseInt(rating[0])) && movies.find(Integer.parseInt(rating[1]))) {
                count += 1;
                sum += Integer.parseInt(rating[2]);
            }
        }

        if (count != 0){
            average = sum/count;
        }
    }

    public void showResult () {
        System.out.println("Sum: "+sum+" Count:"+count+" Average: "+average);
    }
}
