package com.framework.stepdefinitions;

import io.cucumber.java.en.*;
import com.opencsv.exceptions.CsvValidationException;
import com.framework.CSVComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CSVComparisonSteps {

    private static final Logger logger = LogManager.getLogger(CSVComparisonSteps.class);

    private List<String[]> csv1;
    private List<String[]> csv2;
    private Map<String, String[]> differences;

    private final CSVComparator csvComparator = new CSVComparator();

    @Given("the first CSV file is {string}")
    public void the_first_csv_file_is(String csvFileName) throws IOException, CsvValidationException {
        String filePath = "src/test/resources/csv/" + csvFileName;
        logger.info("Loading first CSV file: {}", csvFileName);
        csv1 = csvComparator.readCSV(filePath);
    }

    @Given("the second CSV file is {string}")
    public void the_second_csv_file_is(String csvFileName) throws IOException, CsvValidationException {
        String filePath = "src/test/resources/csv/" + csvFileName;
        logger.info("Loading second CSV file: {}", csvFileName);
        csv2 = csvComparator.readCSV(filePath);
    }

    @When("I compare the two CSV files")
    public void i_compare_the_two_csv_files() {
        logger.info("Initiating CSV file comparison");
        differences = csvComparator.compareCSVFiles(csv1, csv2);
    }

    @Then("I should see the differences as follows:")
    public void i_should_see_the_differences_as_follows(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedDifferences = dataTable.asMaps(String.class, String.class);
        logger.info("Validating CSV differences");

        for (Map<String, String> expected : expectedDifferences) {
            String event = expected.get("Event");
            String[] expectedValues = new String[]{expected.get("Gold"), expected.get("Silver"), expected.get("Bronze")};

            logger.debug("Checking differences for event: {}", event);

            if (differences.containsKey(event)) {
                assertThat(differences.get(event)).containsExactly(expectedValues);
            } else {
                logger.error("Expected differences for event {} not found", event);
                fail("Expected differences for event " + event + " not found");
            }
        }
    }
}
