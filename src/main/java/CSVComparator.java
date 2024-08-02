package com.framework;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVComparator {

    public List<String[]> readCSV(String filePath) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = new ArrayList<>();
            String[] line;
            while ((line = reader.readNext()) != null) {
                records.add(line);
            }
            return records;
        }
    }

    public Map<String, String[]> compareCSVFiles(List<String[]> csv1, List<String[]> csv2) {
        Map<String, String[]> differences = new HashMap<>();

        Map<String, String[]> map1 = listToMap(csv1);
        Map<String, String[]> map2 = listToMap(csv2);

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                if (!Arrays.equals(map1.get(key), map2.get(key))) {
                    differences.put(key, map2.get(key));
                    System.out.println("Difference found in event: " + key);
                }
            } else {
                differences.put(key, null);
                System.out.println("Event " + key + " only present in the first CSV");
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                differences.put(key, map2.get(key));
                System.out.println("Event " + key + " only present in the second CSV");
            }
        }

        return differences;
    }

    private Map<String, String[]> listToMap(List<String[]> list) {
        Map<String, String[]> map = new HashMap<>();
        for (String[] entry : list) {
            map.put(entry[0], Arrays.copyOfRange(entry, 1, entry.length));
        }
        return map;
    }
}
