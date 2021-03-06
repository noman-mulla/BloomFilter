package com.nomanmulla;

import com.nomanmulla.bloom.filter.BloomFilter;
import com.nomanmulla.bloom.filter.SimpleBloomFilter;
import com.nomanmulla.hash.functions.HashFunction;
import com.nomanmulla.hash.functions.HashFunctions;
import com.nomanmulla.utils.HashUtil;
import com.nomanmulla.utils.csv.CSVWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Application {

    private static List<String> csvGenList;

    public static void main(String[] args) {
        int inputSize = 70000;

        int[] multipliers = {10};
        int[] inputSizes = new int[multipliers.length];
        for (int i = 0; i < multipliers.length; i++) {
            inputSizes[i] = inputSize * multipliers[i];
        }
        //getting csv writer
        CSVWriter csvWriter = CSVWriter.getInstance();
        csvWriter.createCSVFile();
        for (int size : inputSizes) {
            log.info("For Size {} ", size);
            //runForCombination(size,1);
            //runForCombination(size,2);
            runForCombination(size,3);
            csvWriter.completePrint();
        }
    }

    private static void runForCombination(int size,int hashCombo) {
        List<String[]> hashCombinations = HashUtil.getCombination(HashFunctions.values(), HashFunctions.values().length, hashCombo);
        commonGenerator(hashCombinations, size);
    }

    private static void commonGenerator(List<String[]> hashCombinations, int size) {
        hashCombinations.forEach(hashCombo -> {
            List<HashFunction<String>> hashFunctionList = new ArrayList<>();
            StringBuffer hashComboString = new StringBuffer();
            for (String hash : hashCombo) {
                hashFunctionList.add(HashUtil.getHashObject(HashFunctions.valueOf(hash)));
                hashComboString.append(hash + " ");
            }
            log.info("For Hash Combo {} ", hashComboString.toString());
            csvGenList = new ArrayList<String>();
            csvGenList.add(String.valueOf(size));
            csvGenList.add(String.valueOf(size));
            csvGenList.add(hashComboString.toString());
            BloomFilter<String> bloomFilter = new SimpleBloomFilter<>(size, 0.1, hashFunctionList);
            readAndTest(bloomFilter);
        });
    }

    private static void readAndTest(BloomFilter<String> bloomFilter) {
        readInputAndAdd(bloomFilter);
        log.info(bloomFilter.toBloomString());
        log.info("False Positive Rate Testing");
        testForFalsePositive(bloomFilter);
    }

    private static void readInputAndAdd(BloomFilter<String> bloomFilter) {

        BufferedReader br = readFile("src/main/resources/query.csv");
        if (null != br) {
            br.lines().skip(1).forEach(l -> {
                bloomFilter.add(l);
            });
        }
    }

    private static void testForFalsePositive(BloomFilter<String> bloomFilter) {
        BufferedReader br = readFile("src/main/resources/test.csv");
        if (null != br) {
            AtomicInteger falseNos = new AtomicInteger(0);
            AtomicInteger testLines = new AtomicInteger(0);
            br.lines().skip(1).forEach(l -> {
                testLines.incrementAndGet();
                if (bloomFilter.contains(l)) {
                    falseNos.incrementAndGet();
                }
            });
            log.info("False Nos {} ", falseNos.get());
            log.info("Test Lines {} ", testLines.get());
            csvGenList.add(String.valueOf(testLines.get()));
            csvGenList.add(String.valueOf(falseNos.get()));
            double falsePositiveRate = falseNos.doubleValue() / testLines.doubleValue();
            log.info("False positive rate {} ", falsePositiveRate);
            csvGenList.add(String.valueOf(falsePositiveRate));
            CSVWriter.getInstance().writeLine(csvGenList);
        }

    }

    private static BufferedReader readFile(String path) {
        File file = new File(path);
        try {
            InputStream stream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            return br;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
