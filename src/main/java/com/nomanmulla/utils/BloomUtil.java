package com.nomanmulla.utils;

public class BloomUtil {

    public static long getOptimalBitArraySize(long expectedNumberOfElements,double fpr){
        //m - Bit Array Size
        return (long) Math.ceil((expectedNumberOfElements * Math.abs(Math.log(fpr))) / (Math.pow(Math.log(2),2)));
    }

    public static int getOptimalHashFunctions(long expectedNumberOfElements,double fpr){
        return (int) Math.ceil((getOptimalBitArraySize(expectedNumberOfElements,fpr)/expectedNumberOfElements)* Math.log(2));
    }





}
