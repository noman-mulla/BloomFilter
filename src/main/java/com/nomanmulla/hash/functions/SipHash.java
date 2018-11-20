package com.nomanmulla.hash.functions;

import com.google.common.hash.Hashing;

public class SipHash<T> implements HashFunction{
    @Override
    public long hash(Object value) {
        byte[] bytes = convertToByte(value);
        return Hashing.sipHash24().hashBytes(bytes).asInt();
    }
}
