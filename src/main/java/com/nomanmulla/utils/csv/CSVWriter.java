package com.nomanmulla.utils.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CSVWriter {

    private static CSVWriter csvWriter;
    private CSVPrinter printer;

    private CSVWriter() {
    }

    public static CSVWriter getInstance() {
        if (csvWriter == null) {
            csvWriter = new CSVWriter();
        }
        return csvWriter;
    }

    public void createCSVFile() {
        try {
            String[] headers = Arrays.stream(Headers.values()).map(Enum::name).toArray(String[]::new);
            FileWriter out = new FileWriter("src/main/resources/output.csv");
            this.printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(List<String> record) {
        try {
            printer.printRecord(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void completePrint() {
        try {
            printer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
