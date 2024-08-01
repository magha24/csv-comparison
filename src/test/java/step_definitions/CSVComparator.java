package step_definitions;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVComparator {
    public static List<String[]> compareCSVFiles(String filePath1, String filePath2) throws IOException, CsvValidationException {
        List<String[]> differences = new ArrayList<>();

        try (CSVReader reader1 = new CSVReader(new FileReader(filePath1));
             CSVReader reader2 = new CSVReader(new FileReader(filePath2))) {

            String[] line1;
            String[] line2;
            int rowNum = 1;

            while ((line1 = reader1.readNext()) != null & (line2 = reader2.readNext()) != null) {
                if (!compareLines(line1, line2)) {
                    differences.add(new String[]{String.valueOf(rowNum), formatDifference(line1, line2)});
                }
                rowNum++;
            }

            while ((line1 = reader1.readNext()) != null) {
                differences.add(new String[]{String.valueOf(rowNum), "Extra row in first file: " + String.join(", ", line1)});
                rowNum++;
            }

            while ((line2 = reader2.readNext()) != null) {
                differences.add(new String[]{String.valueOf(rowNum), "Extra row in second file: " + String.join(", ", line2)});
                rowNum++;
            }
        }

        return differences;
    }

    private static boolean compareLines(String[] line1, String[] line2) {
        if (line1.length != line2.length) {
            return false;
        }

        for (int i = 0; i < line1.length; i++) {
            if (!line1[i].equals(line2[i])) {
                return false;
            }
        }

        return true;
    }

    private static String formatDifference(String[] line1, String[] line2) {
        StringBuilder sb = new StringBuilder();
        sb.append("File1: [");
        sb.append(String.join(", ", line1));
        sb.append("] vs File2: [");
        sb.append(String.join(", ", line2));
        sb.append("]");
        return sb.toString();
    }
}
