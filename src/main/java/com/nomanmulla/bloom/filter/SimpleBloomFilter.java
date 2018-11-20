package com.nomanmulla.bloom.filter;


import com.nomanmulla.hash.functions.HashFunction;
import com.nomanmulla.utils.HashUtil;

import java.util.BitSet;
import java.util.List;

public class SimpleBloomFilter<T> implements BloomFilter<T> {


    protected BitSet bloomSet;
    protected static long expectedElements;
    protected double expectedFalsePositiveRate;
    protected List<HashFunction<T>> hashFunctionList;

    public SimpleBloomFilter(long expectedElements, double expectedFalsePositiveRate, List<HashFunction<T>> hashFunctionList) {
        this.expectedElements = expectedElements;
        this.expectedFalsePositiveRate = expectedFalsePositiveRate;
        bloomSet = new BitSet((int) expectedElements);
        this.hashFunctionList = hashFunctionList;
    }


    public void add(T value) {
        hashFunctionList.forEach(tHashFunction -> {
            long index = HashUtil.convertWithinExpectedSize(tHashFunction.hash(value), expectedElements);
            bloomSet.set((int) index);
        });

    }


    public boolean contains(T value) {
        return hashFunctionList.stream().filter(tHashFunction -> !bloomSet.get((int) HashUtil.convertWithinExpectedSize(tHashFunction.hash(value), expectedElements))
        ).count() >= 1 ? false : true;
    }

    @Override
    public String toBloomString() {
        return bloomSet.toString();
    }


}
