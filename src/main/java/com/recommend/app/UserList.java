package com.recommend.app;

import java.io.*;
import java.util.*;

public class UserList {
    TreeSet<Integer> users = new TreeSet<Integer>();

    // Extracting userlist from 'users.dat' based on given occupation
    // And store the list into the 'users' member
    public void build(int occupation) throws IOException {
        File usersFile = new File("/root/project/movie_recommender_system/data/users.dat");
        FileReader reader = new FileReader(usersFile);
        BufferedReader buffer = new BufferedReader(reader);
        String line = "";
        while((line = buffer.readLine()) != null){
            String[] user = line.split("::");
            if(Integer.parseInt(user[3]) == occupation) {
                users.add(Integer.parseInt(user[0]));
            }
        }
    }

    public boolean find(int occupation) {
        return users.contains(occupation);
    }
}
