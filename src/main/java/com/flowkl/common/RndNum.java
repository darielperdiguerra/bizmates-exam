package com.flowkl.common;

import java.util.SplittableRandom;

public class RndNum {
    private static RndNum randomNum;

    public static RndNum getRandomNumberUtil(){
        if(randomNum == null){	return randomNum = new RndNum();
        }else{return randomNum;
        }
    }
    public static void setRandomNumberUtilInstanceToNull(){
        randomNum = null;
    }
    public int getRandomNumber(){
        return new SplittableRandom().nextInt(0, 9900);
    }
}
