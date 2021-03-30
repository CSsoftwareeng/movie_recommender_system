package com.recommend.app;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import com.recommend.utils.errors.ArgNotExistError;

public class Arguments {
    List<String> genres = new ArrayList<String>();
    String occupation = null;

    String raw_genres = null;
    String raw_occupation = null;

    final static List<String> GENRE_LIST = new ArrayList<String>() {
        {
            add("action");
            add("adventure");
            add("animation");
            add("children's");
            add("comedy");
            add("crime");
            add("documentary");
            add("drama");
            add("fantasy");
            add("film-noir");
            add("filmnoir");
            add("horror");
            add("musical");
            add("mystery");
            add("romance");
            add("sci-fi");
            add("scifi");
            add("thriller");
            add("war");
            add("western");
        }
    };
    final static List<String> OCCUPATIONS_LIST = new ArrayList<String>() {
        {
            add("other");
            add("academic/educator");
            add("academiceducator");
            add("artist");
            add("clerical/admin");
            add("clericaladmin");
            add("college/gradstudent");
            add("collegegradstudent");
            add("customerservice");
            add("doctor/healthcare");
            add("doctorhealthcare");
            add("executive/managerial");
            add("executivemanagerial");
            add("farmer");
            add("homemaker");
            add("k-12student");
            add("lawyer");
            add("programmer");
            add("retired");
            add("sales/marketing");
            add("salesmarketing");
            add("scientist");
            add("self-employed");
            add("selfemployed");
            add("technician/engineer");
            add("technicianengineer");
            add("tradesman/craftsman");
            add("tradesmancraftsman");
            add("unemployed");
            add("writer");
        }
    };

    public Arguments(String arg1, String arg2) {
        setArgs(arg1, arg2);
        parseArgs();
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
        this.occupation = st2.trim().toLowerCase().replaceAll("\\p{Z}", "");

        for (String genre : this.genres) {
            if (!GENRE_LIST.contains(genre)) {
                throw new ArgNotExistError(genre, raw_genres,true);
            }
        }
        if (!OCCUPATIONS_LIST.contains(this.occupation)) throw new ArgNotExistError(this.occupation, raw_occupation, false);
    }
}
