package com.framework;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVComparator {

    private static final Logger logger = LogManager.getLogger(CSVComparator.class);

    public List<String[]> readCSV(String filePath) throws IOException, CsvValidationException {
        logger.info("Reading CSV file from path: {}", filePath);

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = new ArrayList<>();
            String[] line;
            while ((line = reader.readNext()) != null) {
                records.add(line);
            }
            logger.info("Successfully read CSV file: {}", filePath);
            return records;
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file: {}", filePath, e);
            throw e;
        }
    }

    public Map<String, String[]> compareCSVFiles(List<String[]> csv1, List<String[]> csv2) {
        logger.info("Comparing CSV files");
        Map<String, String[]> differences = new HashMap<>();

        Map<String, String[]> map1 = listToMap(csv1);
        Map<String, String[]> map2 = listToMap(csv2);

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                if (!Arrays.equals(map1.get(key), map2.get(key))) {
                    differences.put(key, map2.get(key));
                    logger.debug("Difference found in event: {}", key);
                }
            } else {
                differences.put(key, null);
                logger.debug("Event {} only present in the first CSV", key);
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                differences.put(key, map2.get(key));
                logger.debug("Event {} only present in the second CSV", key);
            }
        }

        logger.info("CSV comparison completed with {} differences found", differences.size());
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
