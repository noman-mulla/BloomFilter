package com.nomanmulla.hash.functions;

import net.openhft.hashing.LongHashFunction;

public class CityHash<T> implements HashFunction<T> {
    @Override
    public long hash(T value) {
        byte[] bytes = convertToByte(value);
        return LongHashFunction.city_1_1().hashBytes(bytes);
    }
}
