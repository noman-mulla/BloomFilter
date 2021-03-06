package com.nomanmulla.hash.functions;

import net.openhft.hashing.LongHashFunction;

public class Murmur3Hash<T> implements HashFunction<T> {


    @Override
    public long hash(T value) {
        byte[] bytes = convertToByte(value);
        return LongHashFunction.murmur_3().hashBytes(bytes);
    }
}
