package com.recommend.app;

import java.io.*;
import java.util.*;

public class RatingCalculator {
    int sum = 0;
    int count = 0;
    double average = 0;
    HashMap<Integer,Integer> sum_map = new HashMap <Integer,Integer>();
    HashMap<Integer,Integer> count_map = new HashMap <Integer,Integer>();
    HashMap<Integer,Double> aver_map = new HashMap <Integer,Double>();


    public RatingCalculator(MovieList movieList, UserList userList) {
        calcAverageHash(movieList, userList);
    }

    void calcAverage (MovieList movies, UserList users) {
        try {
            File usersFile = new File("./data/ratings.dat");
            FileReader reader = new FileReader(usersFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] rating = line.split("::");
                if (users.find(Integer.parseInt(rating[0])) && movies.find(Integer.parseInt(rating[1]))) {
                    count += 1;
                    sum += Integer.parseInt(rating[2]);
                }
            }
        } catch (IOException e) {}

        if (count != 0){
            average = (double)sum/count;
        }
    }

    void calcAverageHash (MovieList movies, UserList users) {
        try {
            File usersFile = new File("./data/ratings.dat");
            FileReader reader = new FileReader(usersFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] rating = line.split("::");
                if (users.find(Integer.parseInt(rating[0])) && movies.find(Integer.parseInt(rating[1]))) {
                    count += 1;
                    sum += Integer.parseInt(rating[2]);
                }
            }
        } catch (IOException e) {}

        if (count != 0){
            average = (double)sum/count;
        }
    }

    public void showResult () {
        System.out.println("Sum: "+sum+" Count: "+count+" Average: "+average);
    }
}
