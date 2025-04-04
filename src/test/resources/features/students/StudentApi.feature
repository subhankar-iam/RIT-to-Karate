
Feature: Student API Tests

  @API_TESTING
  Background:
    * def baseUrl = 'http://localhost:8080'
    * def contentType = 'application/json'
    * configure headers = { 'Content-Type': contentType }

  @API_TESTING
  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('classpath:features/requests/students/add_student.json')
    When method POST
    Then status 201

  @API_TESTING
  Scenario: Get student details
    Given path '/add/student/get'
    And request read('classpath:features/requests/students/get_student.json')
    When method GET
    Then status 200

