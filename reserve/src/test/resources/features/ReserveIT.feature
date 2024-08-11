Feature: E2E Test for reserve service

  @message
  Scenario: User reserves flight
    Given User books a flight
    Then Correct booking detail is returned
    And flight capacity is reduced by seat count