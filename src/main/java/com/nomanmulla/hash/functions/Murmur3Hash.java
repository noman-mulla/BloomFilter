package com.nomanmulla.hash.functions;

import org.apache.hive.common.util.Murmur3;

public class Murmur3Hash<T> implements HashFunction<T> {


    @Override
    public long[] hash(T value) {
        byte[] bytes = convertToByte(value);
        return Murmur3.hash128(bytes);
    }
}
