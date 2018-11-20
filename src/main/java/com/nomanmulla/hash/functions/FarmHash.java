package com.nomanmulla.hash.functions;

import net.openhft.hashing.LongHashFunction;

public class FarmHash<T> implements HashFunction {


    @Override
    public long hash(Object value) {
        byte[] bytes = convertToByte(value);
        return LongHashFunction.farmNa().hashBytes(bytes);
    }
}
