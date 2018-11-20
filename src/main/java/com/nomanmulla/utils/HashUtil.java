package com.nomanmulla.utils;

import com.nomanmulla.hash.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HashUtil {

    public static long convertWithinExpectedSize(long hashValue, long expectedSize) {
        return Math.abs(hashValue % expectedSize);
    }

    public static HashFunction getHashObject(HashFunctions hashEnum) {
        if (hashEnum.getHash().equals("CITYHASH")) {
            return new CityHash();
        } else if (hashEnum.getHash().equals("MURMUR3")) {
            return new Murmur3Hash();
        } else if (hashEnum.getHash().equals("FARMHASH")) {
            return new FarmHash();
        } else if (hashEnum.getHash().equals("XXXHASH")) {
            return new XXXHash();
        } else if (hashEnum.getHash().equals("ADLERHASH")) {
            return new AdlerHash();
        } else if (hashEnum.getHash().equals("CRCHASH")) {
            return new CRCHash();
        } else if (hashEnum.getHash().equals("SIPHASH")) {
            return new SipHash();
        } else if (hashEnum.getHash().equals("JENKINSHASH")) {
            return new JenkinsHash();
        }
        return null;
    }

    private static List<String[]> combinationUtil(String arr[], int n, int r,
                                                  int index, String data[], int i, List<String[]> hashCombinations) {

        if (index == r) {
            String comb[] = new String[r];
            for (int j = 0; j < r; j++)
                comb[j] = data[j];
            hashCombinations.add(comb);
            return hashCombinations;
        }

        if (i >= n)
            return hashCombinations;

        data[index] = arr[i];
        combinationUtil(arr, n, r, index + 1,
                data, i + 1, hashCombinations);

        combinationUtil(arr, n, r, index, data, i + 1, hashCombinations);
        return hashCombinations;
    }

    public static List<String[]> getCombination(HashFunctions[] hashes, int n, int r) {
        String[] arr = Stream.of(hashes).map(hash -> hash.getHash()).toArray(String[]::new);
        String data[] = new String[r];
        List<String[]> hashCombinations = new ArrayList<>();
        hashCombinations = combinationUtil(arr, n, r, 0, data, 0, hashCombinations);
        return hashCombinations;
    }


}
