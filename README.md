
# CSV Comparison Automation Framework

## Overview

This project is an automation framework built in Java to compare two CSV files and identify differences. It uses Cucumber for behavior-driven development (BDD), JUnit for testing, and OpenCSV for CSV file handling. The framework is designed to be flexible and easy to configure, allowing for dynamic file paths and test scenarios.

## Project Structure

The project is structured as follows:

- src/test/java: Contains the test code, including step definitions and test runner.
- src/test/resources: Contains test resources like feature files, csv files and configuration properties.
- logs: Contains log files generated during test execution.


## Features

- **CSV Comparison:** Compares two CSV files and reports differences.
- **Dynamic Configuration:** File paths and test scenarios can be configured dynamically using a properties file and Cucumber feature files.
- **Behavior-Driven Development (BDD):** Uses Cucumber for writing test scenarios in Gherkin syntax.
- **Automated Testing:** Integrates JUnit for running automated tests.
- **Detailed Reporting:** Generates HTML test reports for test results visualization.
- **Logging:** Utilizes Log4J for logging information, errors, and debug messages to both console and log files.

## Getting Started

### Prerequisites

- **Java JDK 8 or higher**
- **Maven 3.6 or higher**
- An IDE like **IntelliJ IDEA** or **Eclipse**

### Installation

1. **Clone the Repository:**

  ```bash
   git clone <https://github.com/magha24/csv-comparison.git>
   cd csv-comparison-framework 
   ```

2. **Build the Project:**

Use Maven to build the project and install dependencies. Run the following command in the terminal:

   ```bash
    mvn clean install
   ```

3. **Ensure Log Directory Exists:**

Create a logs directory in the project root to store log files:

```bash
 mkdir logs
   ``` 

4. **Run Tests:**

Tests can be executed directly from your IDE or using the Maven command:

   ```bash
    mvn test
   ```

## Configuration

### CSV Files

CSV files for comparison should be placed in the src/test/resources/csv directory. You can specify different CSV files by updating the scenario outlines in the feature files.

### Properties File

The cucumber.properties file in src/test/resources is used to configure Cucumber settings.

### Log4J Configuration
The log4j2.properties file in src/test/resources configures Log4J logging. It specifies how log messages are formatted and where they are output (console and file). You can adjust logging levels and outputs by modifying this file.

### Test Reports
HTML reports are generated in the target/cucumber-reports directory after tests are run. Open the HTML file in your browser to view detailed test results.

## Implementation Details

### CSV Comparison Logic
The CSVComparator class reads CSV files and compares their content. Differences are identified and reported, with specific emphasis on matching and differing records.

### Cucumber Scenarios
Cucumber scenarios are defined in Gherkin syntax in the feature file located at src/test/resources/features/CSVComparison.feature. This file outlines test cases and expected outcomes.

### Step Definitions
The CSVComparisonSteps class in src/test/java/com/framework/stepdefinitions provides the implementation of the steps defined in the feature file, using the CSVComparator class for comparison logic.

### Logging
Log4J is used throughout the project to log informational, debug, and error messages. Log output can be customized through the log4j2.properties configuration.

### Notes
Ensure the CSV files are properly formatted and located in the correct directory.
Update the feature file with appropriate test scenarios to reflect your testing needs.

### Contribution
If you'd like to contribute to this project, please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.
