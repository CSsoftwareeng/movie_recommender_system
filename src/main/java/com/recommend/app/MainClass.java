package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import com.recommend.utils.errors.ArgNotExistError;

import java.util.Arrays;


public class MainClass {
    public static void main(String[] args) throws ArgCntError,ArgNotExistError  {
        try {
            if (args.length != 2) {
                throw new ArgCntError(args, args.length);
            }
            Arguments arg = new Arguments(args[0], args[1]);
            System.out.println("genres : " + arg.genres);
            System.out.println("occupations : " + arg.occupation);
        }catch (ArgCntError e) {
            System.out.println("[ERROR] The number of argument should be 2.");
            System.out.println("You have currently put " + e.number + "argument(s).");
            System.out.println("Your arguments : " + Arrays.toString(e.user_input));
        }catch (ArgNotExistError e) {
            if (e.getArgType().equals("genre")) {
                System.out.println("[ERROR] The movie genre [" + e.user_input + "] is invalid.");
                System.out.println("Can't find [" + e.processed_input + "] in the list.");
            }
            else {
                System.out.println("[ERROR] Can't find [" + e.user_input + "] in the available occupation list.");
            }
        }finally {
            System.out.println("----------Program is terminated.-----------\n\n");
        }
    }
}

