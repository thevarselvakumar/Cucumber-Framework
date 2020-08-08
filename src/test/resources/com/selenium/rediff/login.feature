@Login
Feature: Logging in to Rediff Money

  @Login
  Scenario Outline: Logging into Rediff
    Given I open <Browser>
    And I go to loginURL
    And I Login inside application
    |thevarselvakumar@gmail.com| Winter123!|
    Then Login should be <Result>

    Examples:
      |Browser |   Result     |
      |chrome  |   success    |
      |chrome  |   success    |