#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Portfolio
Feature: Title of your feature
  I want to use this template for my feature file
	
	Background:
    Given I open chrome
    And I go to loginURL
    And I Login inside application
    |ashishthakur1983| pass@1234|

  @CreatePortFolio
  Scenario: Creating a Portfolio
    And I click createPortFolio_id and wait for portfolioName_id
    And I clear portfolioName_id
    And I type test2 in portfolioName_id field
    And I click on portFolioSubmit_id

  @DeletePortFolio
  Scenario: Deleting a Portfolio
    And I wait for page to load
    And I select test2 from portfolioSelection_id
    And I wait for page to load
    And I click on deletePortfolio_id
    When I accept alert
#  Then Portfolio should be deleted