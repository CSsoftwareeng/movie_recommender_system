package com.recommend.app;

import java.io.*;
import java.util.*;

public class RatingCalculator {
    int sum = 0;
    int count = 0;
    double average = 0;
    HashMap<Integer,Integer> sum_map = new HashMap <>();
    HashMap<Integer,Integer> count_map = new HashMap <>();
    HashMap<Integer,Double> aver_map = new HashMap <>();
    LinkedHashMap<Integer, String> result = new LinkedHashMap<>();

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
                if (users.find(Integer.parseInt(rating[0])) && movies.findID(Integer.parseInt(rating[1]))) {
                    int movie = Integer.parseInt(rating[1]);
                    if(sum_map.containsKey(movie))
                    {
                        int tsum = sum_map.get(movie) + Integer.parseInt(rating[2]);
                        int tcount = count_map.get(movie) + 1;
                        sum_map.put(movie, tsum);
                        count_map.put(movie, tcount);
                        count += 1;
                    }
                    else
                    {
                        sum_map.put(movie,Integer.parseInt(rating[2]));
                        count_map.put(movie,1);
                        count += 1;
                    }
                }
            }
        } catch (IOException e) {}

        if (count != 0){
            for(Integer key : sum_map.keySet())
    	    {
    		    double temp = (double)sum_map.get(key) / count_map.get(key);
    		    aver_map.put(key,temp);

    	    }

            List<Map.Entry<Integer, Double>> entries = new LinkedList<>(aver_map.entrySet());
            Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                
            for (Map.Entry<String, Double> entry : entries) {
                    List<Integer> idlist = new ArrayList<Integer>();
                    List<String> namelist = new ArrayList<String>();
                    idlist.add(entry.getKey());
                    movies.searchName(idlist);
                    namelist = moives.getMoviesName();
                    result.put(entry.getKey(), namelist.get(0));
            }
        }
    }

    public void showResult () {
        String moviename;
        String movielink;
        System.out.println("Sum: "+sum+" Count: "+count+" Average: "+average);
    }
}
