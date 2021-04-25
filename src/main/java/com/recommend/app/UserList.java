package com.recommend.app;

import java.io.*;
import java.util.*;

import com.recommend.utils.errors.UserNotExistError;

public class UserList {
    TreeSet<Integer> users = new TreeSet<Integer>();

    public UserList(int occupation) {
        build(occupation);
    }

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

    void build(char gender, int age, int occupation) throws UserNotExistError {
        bool isGenderUnassigned = (gender == 'X');
        bool isAgeUnassigned = (age == -1);
        bool isOccupationUnassigned = (occupation == -1);

        try {
            File usersFile = new File("./data/users.dat");
            FileReader reader = new FileReader(usersFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] user = line.split("::");
                if ((isGenderUnassigned || user[1].charAt(0) == gender) && (isAgeUnassigned || Integer.parseInt(user[2]) == age) && (isOccupationUnassigned || Integer.parseInt(user[3]) == occupation)) {
                    users.add(Integer.parseInt(user[0]));
                }
            }
        } catch (IOException e) {}
    }

    public boolean find(int userid) {
        return users.contains(userid);
    }

    public void printUsers() {
	    System.out.println(users);
    }
}
