Feature: search for invalid value on insurance portal page

  Scenario: search with invalid value
    Given user is on portal page "https://covercheck.vwfsinsuranceportal.co.uk/"
    And searches for "O"
    When user clicks on Find Vehicle button
    Then the result should be displayed and saved in csv file