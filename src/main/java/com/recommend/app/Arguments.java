package com.recommend.app;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

import com.recommend.utils.errors.ArgNotExistError;

public class Arguments {
    List<String> genres = new ArrayList<String>();
    int occupation;

    String raw_genres = null;
    String raw_occupation = null;
    public String err_sender_str = null;

    final static Map<String, String> GENRE_MAP = new HashMap<String, String>();
    final static Map<String, Integer> OCCUPATIONS_MAP = new HashMap<String, Integer>();

    public Arguments(String arg1, String arg2) {
        setMap();
        setArgs(arg1, arg2);
        parseArgs();
    }
    void setMap() {
        GENRE_MAP.put("action", "Action"); GENRE_MAP.put("adventure", "Adventure"); GENRE_MAP.put("animation", "Animation");
        GENRE_MAP.put("children's", "Children's"); GENRE_MAP.put("comedy", "Comedy"); GENRE_MAP.put("crime", "Crime");
        GENRE_MAP.put("documentary", "Documentary"); GENRE_MAP.put("drama", "Drama"); GENRE_MAP.put("fantasy", "Fantasy");
        GENRE_MAP.put("film-noir", "Film-Noir"); GENRE_MAP.put("filmnoir", "Film-Noir"); GENRE_MAP.put("horror", "Horror");
        GENRE_MAP.put("musical", "Musical"); GENRE_MAP.put("mystery", "Mystery"); GENRE_MAP.put("romance", "Romance");
        GENRE_MAP.put("sci-fi", "Sci-Fi"); GENRE_MAP.put("scifi", "Sci-Fi"); GENRE_MAP.put("thriller", "Thriller");
        GENRE_MAP.put("war", "War"); GENRE_MAP.put("western", "Western");
    
        OCCUPATIONS_MAP.put("other", 0); OCCUPATIONS_MAP.put("academic/educator", 1); OCCUPATIONS_MAP.put("academiceducator", 1);
        OCCUPATIONS_MAP.put("academic", 1); OCCUPATIONS_MAP.put("educator", 1); OCCUPATIONS_MAP.put("artist", 2);
        OCCUPATIONS_MAP.put("clerical/admin", 3); OCCUPATIONS_MAP.put("clericaladmin", 3); OCCUPATIONS_MAP.put("clerical", 3);
        OCCUPATIONS_MAP.put("admin", 3); OCCUPATIONS_MAP.put("college/gradstudent", 4); OCCUPATIONS_MAP.put("collegegradstudent", 4);
        OCCUPATIONS_MAP.put("collegestudent", 4); OCCUPATIONS_MAP.put("gradstudent", 4); OCCUPATIONS_MAP.put("customerservice", 5);
        OCCUPATIONS_MAP.put("doctor/healthcare", 6); OCCUPATIONS_MAP.put("doctorhealthcare", 6); OCCUPATIONS_MAP.put("doctor", 6);
        OCCUPATIONS_MAP.put("healthcare", 6); OCCUPATIONS_MAP.put("executive/managerial", 7); OCCUPATIONS_MAP.put("executivemanagerial", 7);
        OCCUPATIONS_MAP.put("executive", 7); OCCUPATIONS_MAP.put("managerial", 7); OCCUPATIONS_MAP.put("farmer", 8);
        OCCUPATIONS_MAP.put("homemaker", 9); OCCUPATIONS_MAP.put("k-12student", 10); OCCUPATIONS_MAP.put("k12student", 10);
        OCCUPATIONS_MAP.put("lawyer", 11); OCCUPATIONS_MAP.put("programmer", 12); OCCUPATIONS_MAP.put("retired", 13);
        OCCUPATIONS_MAP.put("sales/marketing", 14); OCCUPATIONS_MAP.put("salesmarketing", 14); OCCUPATIONS_MAP.put("sales", 14);
        OCCUPATIONS_MAP.put("marketing", 14); OCCUPATIONS_MAP.put("scientist", 15); OCCUPATIONS_MAP.put("self-employed", 16);
        OCCUPATIONS_MAP.put("selfemployed", 16); OCCUPATIONS_MAP.put("technician/engineer", 17); OCCUPATIONS_MAP.put("technicianengineer", 17);
        OCCUPATIONS_MAP.put("technician", 17); OCCUPATIONS_MAP.put("engineer", 17); OCCUPATIONS_MAP.put("tradesman/craftsman", 18);
        OCCUPATIONS_MAP.put("tradesmancraftsman", 18); OCCUPATIONS_MAP.put("tradesman", 18); OCCUPATIONS_MAP.put("craftsman", 18); 
        OCCUPATIONS_MAP.put("unemployed", 19); OCCUPATIONS_MAP.put("writer", 20);
    }
    void setArgs(String arg1, String arg2) {
        this.raw_genres = arg1;
        this.raw_occupation = arg2;
    }
    void parseArgs() throws ArgNotExistError{
        String st1 = raw_genres;
        String st2 = raw_occupation;

        StringTokenizer gen_st = new StringTokenizer(st1, "|");
        while(gen_st.hasMoreTokens()) {
            this.genres.add(gen_st.nextToken().trim().toLowerCase().replaceAll("\\p{Z}", ""));
        }
        st2 = st2.trim().toLowerCase().replaceAll("\\p{Z}", "");

        for (int i = 0; i < genres.size(); i++) {
            String genre = genres.get(i);
            if (!GENRE_MAP.containsKey(genre)) {
                this.err_sender_str = genre;
                throw new ArgNotExistError( this,true);
            }
            else {
                genres.set(i, GENRE_MAP.get(genre));
            }
        }
        if (!OCCUPATIONS_MAP.containsKey(st2)) {
            this.err_sender_str = st2;
            throw new ArgNotExistError(this, false);
        }
        else {
            this.occupation = OCCUPATIONS_MAP.get(st2.trim().toLowerCase().replaceAll("\\p{Z}", ""));
        }
    }
    void printArgs() {
        System.out.println("*** arg 1 : " + this.genres + " ***");
        System.out.println("*** arg 2 : " + this.occupation + " ***");
    }
    public String getRawGenre() {
        return this.raw_genres;
    }
    public String getRawOccupation() {
        return this.raw_occupation;
    }

}
