@Login
Feature: Logging in to flight

  @Flight
  Scenario: Logging into flight
    Given I open chrome
    And I go to loginURL
    And I select depaturecity as Paris
    And I select destinationcity as Berlin
    And I click on FindFlights_xpath
    And I click on ChooseFlight_xpath
    