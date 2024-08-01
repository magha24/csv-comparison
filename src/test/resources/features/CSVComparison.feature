Feature: Compare CSV files


  @testCase1
  Scenario: Identical Files
    Given I have CSV files "data/test_case_1/file1.csv" and "data/test_case_1/file2.csv"
    When I compare the CSV files
    Then the files should be identical

  @testCase2
  Scenario: Different Content
    Given I have CSV files "data/test_case_2/file1.csv" and "data/test_case_2/file2.csv"
    When I compare the CSV files
    Then the files should have differences

  @testCase3
  Scenario: Different Number of Rows
    Given I have CSV files "data/test_case_3/file1.csv" and "data/test_case_3/file2.csv"
    When I compare the CSV files
    Then the files should have differences

  @testCase4
  Scenario: Different Number of Columns
    Given I have CSV files "data/test_case_4/file1.csv" and "data/test_case_4/file2.csv"
    When I compare the CSV files
    Then the files should have differences
