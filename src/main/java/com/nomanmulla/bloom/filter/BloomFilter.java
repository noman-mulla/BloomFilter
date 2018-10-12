package com.nomanmulla.bloom.filter;

public interface BloomFilter<T> {

    public void add(T value);

    public boolean contains(T value);


}
