package com.nomanmulla.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BloomUtilTest {

    @Test
    public void getOptimalBitArraySizeTest() {
        assertEquals(BloomUtil.getOptimalBitArraySize(200L, 0.01), 1918);
    }

    @Test
    public void getOptimalHashFunctionsTest() {
        assertEquals(BloomUtil.getOptimalHashFunctions(200L, 0.01), 7);
    }

}
