
Feature: Student API Tests

  @API_TESTING
  Background:
    * url 'http://localhost:8080'
    * header content-type = 'applicatin/json'

  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('classpath:features/requests/students/add_student.json')
    When method POST
    Then status 201

  Scenario: Get student details
    Given path '/add/student/get'
    And request read('classpath:features/requests/students/get_student.json')
    When method GET
    Then status 200

