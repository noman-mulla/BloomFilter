package com.nomanmulla.bloom.filter;


import com.nomanmulla.hash.functions.HashFunction;
import com.nomanmulla.hash.functions.Murmur3Hash;
import com.nomanmulla.utils.HashUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class SimpleBloomFilter<T> implements BloomFilter<T> {


    protected BitSet bloomSet[];
    protected static long expectedElements;
    protected double expectedFalsePositiveRate;
    protected static HashFunction<String> hashFunction;

    SimpleBloomFilter(long expectedElements,double expectedFalsePositiveRate){
        this.expectedElements = expectedElements;
        this.expectedFalsePositiveRate = expectedFalsePositiveRate;
        bloomSet = new BitSet[(int)expectedElements];
        hashFunction = new Murmur3Hash<>();
    }


    public void add(T value) {

    }

    public boolean contains(T value) {
        return false;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String abc= "abcdefg";
        SimpleBloomFilter<String> simpleBloomFilter = new SimpleBloomFilter<>(5L,0.1);
        System.out.println(HashUtil.convertWithinExpectedSize(hashFunction.hash(abc),expectedElements));
    }
}
