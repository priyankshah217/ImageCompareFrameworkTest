Feature: Checking different repo pages on GitHub
  As a QA I want to access different repos on GitHub for Multiple Devices

  Background: GitHub to be validated
    Given I launch "google.com" in chrome
    Then I take a screenshot of "Google Home" screen

  Scenario: "Appium" repository displays in GitHub
    When I enter "Appium Github" in "Google Search" field on "Google Home" screen
    And I click on "Enter" button on "Google Home" screen
    When I click on "Appium GitHub" link on "Google Search Result" screen
    Then I take a screenshot of "Appium GitHub" screen

  Scenario: "Selendroid" repository displays in GitHub
    When I enter "Selendroid Github" in "Google Search" field on "Google Home" screen
    And I click on "Enter" button on "Google Home" screen
    When I click on "Selendroid GitHub" link on "Google Search Result" screen
    Then I take a screenshot of "Selendroid GitHub" screen
