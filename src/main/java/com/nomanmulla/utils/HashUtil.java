package com.nomanmulla.utils;

public class HashUtil {

    public static long convertWithinExpectedSize(long[] hashValue,long expectedSize){
        return Math.abs(hashValue[0] % expectedSize);
    }

}
