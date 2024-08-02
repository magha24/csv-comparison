Feature: CSV File Comparison

  Scenario Outline: Compare two CSV files and identify differences
    Given the first CSV file is "<csv1>"
    And the second CSV file is "<csv2>"
    When I compare the two CSV files
    Then I should see the differences as follows:
      | Event         | Gold          | Silver              | Bronze    |
      | 200m          | Jamaica       | USA                 | Australia |
      | 400m          | Great Britain | Trinidad and Tobago | Jamaica   |

    Examples:
      | csv1                  | csv2                  |
      | paris_olympics_1.csv  | paris_olympics_2.csv  |
