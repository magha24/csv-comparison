package com.framework.stepdefinitions;

import io.cucumber.java.en.*;
import com.opencsv.exceptions.CsvValidationException;
import com.framework.CSVComparator;
import com.framework.ConfigurationLoader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CSVComparisonSteps {

    private List<String[]> csv1;
    private List<String[]> csv2;
    private Map<String, String[]> differences;

    private final ConfigurationLoader configLoader = new ConfigurationLoader();
    private final CSVComparator csvComparator = new CSVComparator();

    @Given("the first CSV file is {string}")
    public void the_first_csv_file_is(String csvFileName) throws IOException, CsvValidationException {
        String filePath = configLoader.getProperty("csv1.path");
        System.out.println("Loading first CSV file from path: " + filePath);
        csv1 = csvComparator.readCSV("src/test/resources/csv/" + csvFileName);
    }

    @Given("the second CSV file is {string}")
    public void the_second_csv_file_is(String csvFileName) throws IOException, CsvValidationException {
        String filePath = configLoader.getProperty("csv2.path");
        System.out.println("Loading second CSV file from path: " + filePath);
        csv2 = csvComparator.readCSV("src/test/resources/csv/" + csvFileName);
    }

    @When("I compare the two CSV files")
    public void i_compare_the_two_csv_files() {
        System.out.println("Comparing CSV files...");
        differences = csvComparator.compareCSVFiles(csv1, csv2);
    }

    @Then("I should see the differences as follows:")
    public void i_should_see_the_differences_as_follows(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedDifferences = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> expected : expectedDifferences) {
            String event = expected.get("Event");
            String[] expectedValues = new String[]{expected.get("Gold"), expected.get("Silver"), expected.get("Bronze")};

            System.out.println("Checking differences for event: " + event);

            if (differences.containsKey(event)) {
                assertThat(differences.get(event)).containsExactly(expectedValues);
            } else {
                fail("Expected differences for event " + event + " not found");
            }
        }
    }
}
