
Feature: E2E Test for flights
    @message
    Scenario: User get flight detail
    Given user requests flight detail by Id
    Then Correct Flight detail is returned

    @message
    Scenario: Admin adds flight detail
    Given Admin adds new flight detail
    Then Correct Flight detail is added