package com.recommend.app;

import java.io.*;
import java.util.*;

import com.recommend.utils.errors.UserNotExistError;

public class UserList {
    TreeSet<Integer> users = new TreeSet<Integer>();

    public UserList(int occupation) {
        build(occupation);
    }

    // Extracting userlist from 'users.dat' based on given occupation
    // And store the list into the 'users' member
    void build(int occupation) throws UserNotExistError {

        try {
            File usersFile = new File("./data/users.dat");
            FileReader reader = new FileReader(usersFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] user = line.split("::");
                if (Integer.parseInt(user[3]) == occupation) {
                    users.add(Integer.parseInt(user[0]));
                }
            }
        } catch (IOException e) {}
    }

    public boolean find(int occupation) {
        return users.contains(occupation);
    }

    public void printUsers() {
	    System.out.println(users);
    }
}
