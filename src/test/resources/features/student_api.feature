
Feature: Student API Tests

  Background:
    * url 'http://localhost:8080'
    * header content-type = 'applicatin/json'

  Scenario: Add a new student
    @API_TESTING
    Given path '/add/student/add'
    And request read('classpath:requests/students/add_student.json')
    When method POST
    Then status 201

  Scenario: Get student details
    @API_TESTING
    Given path '/add/student/get'
    And request read('classpath:requests/students/get_student.json')
    When method GET
    Then status 200

