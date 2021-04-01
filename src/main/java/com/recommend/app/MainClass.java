package com.recommend.app;

import com.recommend.utils.errors.ArgCntError;
import com.recommend.utils.errors.ArgNotExistError;

public class MainClass {
    public static void main(String[] args) throws ArgCntError,ArgNotExistError  {

        if (args.length != 2) {
            throw new ArgCntError(args, args.length);
        }
        Arguments arg = new Arguments(args[0], args[1]);
    }
}

