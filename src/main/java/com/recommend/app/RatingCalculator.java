package com.recommend.app;

import java.io.*;
import java.util.*;

class Rating implements Comparable<Rating>{
    int sum;
    int count;
    double average;
    int match;

    public Rating(int a, int b, int c){
        this.sum = a;
        this.count = b;
        this.match = c;
        this.average = (double)a/b;
    }

    public int getSum(){
        return this.sum;
    }

    public int getCount(){
        return this.count;
    }

    public double getAverage(){
        return this.average;
    }

    public int getMatch(){
        return this.match;
    }


    @Override
    public int compareTo(Rating t){
        if(this.match>t.getMatch()){
            return 1;
        }
        else if(this.match<t.getMatch()){
            return -1;
        }
        else
        {
        	if(this.average>t.getAverage()){
            	return 1;
    		}
        	else if(this.average<t.getAverage()){
            	return -1;
        	}
        }
        return 0;
    }

}



public class RatingCalculator {

    HashMap<Integer,Rating> map = new HashMap <>();
    LinkedHashMap<Integer, Rating> result = new LinkedHashMap<>();

    public RatingCalculator(MovieList movieList, UserList userList) {
        calcAverageHash(movieList, userList);
    }



    void calcAverageHash (MovieList movies, UserList users) {
        try {
            File usersFile = new File("./data/ratings.dat");
            FileReader reader = new FileReader(usersFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            buffer.mark(999999999);
            int matched = 3;
            while ((line = buffer.readLine()) != null) {
                String[] rating = line.split("::");
                if (users.isMatched(Integer.parseInt(rating[0])) && movies.findID(Integer.parseInt(rating[1]))) {
                    int movie = Integer.parseInt(rating[1]);
                    if(map.containsKey(movie))
                    {
                        int tsum = map.get(movie).getSum() + Integer.parseInt(rating[2]);
                        int tcount = map.get(movie).getCount() + 1;
                        map.put(movie, new Rating(tsum,tcount,matched));
                    }
                    else
                    {
                        map.put(movie, new Rating(Integer.parseInt(rating[2]),1,matched));
                    }
                }
            }

            if(map.size() < 10)
            {
                matched = 2;
                buffer.reset();
                while ((line = buffer.readLine()) != null) {
                    String[] rating = line.split("::");
                    if (users.isSimilar(Integer.parseInt(rating[0])) && movies.findID(Integer.parseInt(rating[1]))) {
                        int movie = Integer.parseInt(rating[1]);
                        if(map.containsKey(movie) && map.get(movie).getMatch() == matched)
                        {
                            int tsum = map.get(movie).getSum() + Integer.parseInt(rating[2]);
                            int tcount = map.get(movie).getCount() + 1;
                            map.put(movie, new Rating(tsum,tcount,matched));
                        }
                        else if(!map.containsKey(movie))
                        {
                            map.put(movie, new Rating(Integer.parseInt(rating[2]),1,matched));
                        }
                    }
                }
            }
    
            if(map.size() < 10)
            {
                matched = 1;
                buffer.reset();
                while ((line = buffer.readLine()) != null) {
                    String[] rating = line.split("::");
                    if (users.isLessSimilar(Integer.parseInt(rating[0])) && movies.findID(Integer.parseInt(rating[1]))) {
                        int movie = Integer.parseInt(rating[1]);
                        if(map.containsKey(movie) && map.get(movie).getMatch() == matched)
                        {
                            int tsum = map.get(movie).getSum() + Integer.parseInt(rating[2]);
                            int tcount = map.get(movie).getCount() + 1;
                            map.put(movie, new Rating(tsum,tcount,matched));
                        }
                        else if(!map.containsKey(movie))
                        {
                            map.put(movie, new Rating(Integer.parseInt(rating[2]),1,matched));
                        }
                    }
                }
            }
    
            if(map.size() < 10)
            {
                matched = 0;
                buffer.reset();
                while ((line = buffer.readLine()) != null) {
                    String[] rating = line.split("::");
                    if (users.isNotSimilar(Integer.parseInt(rating[0])) && movies.findID(Integer.parseInt(rating[1]))) {
                        int movie = Integer.parseInt(rating[1]);
                        if(map.containsKey(movie) && map.get(movie).getMatch() == matched)
                        {
                            int tsum = map.get(movie).getSum() + Integer.parseInt(rating[2]);
                            int tcount = map.get(movie).getCount() + 1;
                            map.put(movie, new Rating(tsum,tcount,matched));
                        }
                        else if(!map.containsKey(movie))
                        {
                            map.put(movie, new Rating(Integer.parseInt(rating[2]),1,matched));
                        }
                    }
                }
            }






        } catch (IOException e) {}

        

        List<Map.Entry<Integer, Rating>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                
        for (Map.Entry<Integer, Rating> entry : entries) {
                result.put(entry.getKey(), entry.getValue());
                if(result.size() == 10)
                    break;
        }

    }

    public void showResult (MovieList movies) {
        String moviename = "";
        String movielink = "";
        int i = 0;
        try{
            for(Integer key : result.keySet())
    	        {
                    File linkfile = new File("./data/links.dat");
                    FileReader fileReader = new FileReader(linkfile);
                    BufferedReader bufReader = new BufferedReader(fileReader);
                    String data = "";
                    List<Integer> ID = new ArrayList<>();
    		        ID.add(key);
                    movies.searchName(ID);
                    moviename = movies.getMoviesName().get(i);
                    i += 1;

                    while ((data = bufReader.readLine()) != null) {
                        String[] temp = data.split("::");
                        if(key == Integer.parseInt(temp[0])) {
                            movielink = temp[1];
                            break;
                        }
                    }
                    System.out.println(moviename+" (http://www.imdb.com/title/tt"+movielink+")" + "Rating : " + result.get(key).getAverage());
    	        }
        } catch (IOException e) {}
    }
}
