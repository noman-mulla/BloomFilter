package com.nomanmulla.hash.functions;

import com.google.common.hash.Hashing;

public class CRCHash<T> implements HashFunction {

    @Override
    public long hash(Object value) {
        byte[] bytes = convertToByte(value);
        return Hashing.crc32().hashBytes(bytes).asInt();
    }
}
