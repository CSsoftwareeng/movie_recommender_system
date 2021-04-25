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

    void build(String gender, String age, String occupation) throws UserNotExistError {
        boolean isGenderUnassigned = gender.isEmpty();
        boolean isAgeUnassigned = age.isEmpty();
        boolean isOccupationUnassigned = occupation.isEmpty();

        try {
            File usersFile = new File("./data/users.dat");
            FileReader reader = new FileReader(usersFile);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] user = line.split("::");
                if ((isGenderUnassigned || user[1].equals(gender)) && (isAgeUnassigned || user[2].equals(age)) && (isOccupationUnassigned || user[3].equals(occupation))) {
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
