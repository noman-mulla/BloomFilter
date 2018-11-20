package com.nomanmulla.hash.functions;

import com.google.common.hash.Hashing;

public class AdlerHash<T> implements HashFunction {


    @Override
    public long hash(Object value) {
        byte[] bytes = convertToByte(value);
        return Hashing.adler32().hashBytes(bytes).asInt();
    }
}
