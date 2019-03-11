package com.nomanmulla.utils.csv;

public enum Headers {
    InputSize,
    BloomFilterSize,
    HashFunctions,
    TestSize,
    FalsePosNos,
    FalsePosRate;


    @Override
    public String toString() {
        return String.format("{},{},{},{},{},{}", InputSize
                , BloomFilterSize, HashFunctions, TestSize, FalsePosNos, FalsePosRate);
    }
}
