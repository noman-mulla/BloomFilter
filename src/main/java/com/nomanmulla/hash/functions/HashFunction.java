package com.nomanmulla.hash.functions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public interface HashFunction<T> {

    public long hash(T value);

    default byte[] convertToByte(T value) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b.toByteArray();

    }

}
