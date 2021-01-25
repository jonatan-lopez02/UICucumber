@SmokeTest
Feature: Cucumber run test

  Scenario Outline: Cucumber setup

    Given sign up an user with username and password
    And login and logout with the user
    When add "<product>" from "<category>" to the Cart
    Then the cart had the "<product>" added


    Examples:
      | product     | category |
      | MacBook air | Laptops  |

