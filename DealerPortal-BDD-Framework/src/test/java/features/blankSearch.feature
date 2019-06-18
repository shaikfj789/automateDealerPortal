Feature: search for blank value on insurance portal page

  Scenario: search with blank value
    Given user is on portal page "https://covercheck.vwfsinsuranceportal.co.uk/"
    When user clicks on Find Vehicle button
    Then the error message "Please enter a valid car registration" should be displayed