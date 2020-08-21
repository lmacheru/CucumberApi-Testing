Feature: end to end tests for Dogs api

  Scenario: dog pictures
    Given A Random Breed
    When Get all list
    Then Check if Bulldog is there
    When Retrieve all sub-breeds

  Scenario: pet store
    Given Retrieve all available pets
    Then Confirm name and Category 12 is in the response
    When I add a new pet
    Then The pet is added and ID retrieved
    Then Retrieve info of created pet using id