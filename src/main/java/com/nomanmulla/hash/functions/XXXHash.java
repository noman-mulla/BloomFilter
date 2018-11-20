package com.nomanmulla.hash.functions;

import net.openhft.hashing.LongHashFunction;

public class XXXHash<T> implements HashFunction<T> {

    @Override
    public long hash(T value) {
        byte[] bytes = convertToByte(value);
        return LongHashFunction.xx().hashBytes(bytes);
    }
}
