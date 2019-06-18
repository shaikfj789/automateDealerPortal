Feature: search for valid value on insurance portal page

  Scenario: search with valid value
    Given user is on portal page "https://covercheck.vwfsinsuranceportal.co.uk/"
    And searches for "OV12UYY"
    When user clicks on Find Vehicle button
    Then the result should be displayed and saved in csv file