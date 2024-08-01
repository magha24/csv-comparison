package step_definitions;

import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyStepdefs {
    private String filePath1;
    private String filePath2;
    private List<String[]> differences;
    @Given("I have CSV files {string} and {string}")
    public void iHaveCSVFilesAnd(String file1, String file2) {
        this.filePath1 = file1;
        this.filePath2 = file2;
    }

    @When("I compare the CSV files")
    public void iCompareTheCSVFiles() throws CsvValidationException, IOException {
        differences = CSVComparator.compareCSVFiles(filePath1, filePath2);

    }

    @Then("the files should be identical")
    public void theFilesShouldBeIdentical() {
        assertTrue(differences.isEmpty());
    }

    @Then("the files should have differences")
    public void theFilesShouldHaveDifferences() {
        assertFalse(differences.isEmpty());
    }


}
