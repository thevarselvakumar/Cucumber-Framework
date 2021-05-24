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
    And I type parikshit in name_xpath field
    And I type Mumbai in address_xpath field
    And I type Mumbai in city_xpath field
    And I type Maharahstra in state_xpath field
    And I type 400083 in zipcode_xpath field
    And I type 789456123 in creditcard_xpath field
    And I type Gharat in nameOnCard_xpath field
    And I click on purchaseflight_xpath