Feature: Account Creation Scenarios

  Background: 
    Given the Endpoint URL is "https://apigwuat.warbabank.com/"
    And Authentication is provided with username "mvrktest" and password "Mv#r@ktE$st$30aY"

  Scenario: Creation of Current Accounts CUR01 with KWD Currency
    Given the Request body is provided using for accounts "CreateCurrentAccountCUR01KWD.json"
    When the Request is sent for account creation
    Then the Status Code should be asserted for accounts creation