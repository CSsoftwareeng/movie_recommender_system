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
            arg.printArgs();
        }catch (ArgCntError e) {
            e.errMessage();
        }catch (ArgNotExistError e) {
            e.errMessage();
        }finally {
            System.out.println("-------------Program is terminated.--------------\n");
        }
    }
}

