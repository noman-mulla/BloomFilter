package com.nomanmulla.hash.functions;

import org.apache.hadoop.util.hash.Hash;

public class JenkinsHash<T> implements HashFunction {

    @Override
    public long hash(Object value) {
        byte[] bytes = convertToByte(value);
        return Hash.getInstance(Hash.JENKINS_HASH).hash(bytes);
    }
}
