Feature: Individual Customer Scenarios

  Background: 
    Given the Endpoint URL is "https://apigwuat.warbabank.com/"
    And Authentication is provided with username "mvrktest" and password "Mv#r@ktE$st$30aY"

  Scenario: Creation of Individual Customer
    Given the Request body is provided using "CreateIndividualCustomer.json"
    When the Request is sent
    Then the Status Code should be asserted
    
   Scenario: Fetching the Created Customer
    When the Request is sent with customerid
    Then the Status Code should be asserted with getCustomer
    
    
