
Feature: E2E Test for flights
    @message
    Scenario: User get flight detail
    Given User requests flight detail by Id
    Then Correct Flight detail is returned

    @message
    Scenario: Admin adds flight detail
    Given Admin adds new flight detail
    Then Correct Flight detail is added

    @message
    Scenario: Get all flights by origin place
    Given  User tries get all flights by origin place
    Then Correct Flight details are returned