
Feature: Student Management API

  Background:
    * url 'http://localhost:8000'
    * header content-type = 'applicatin/json'


  @API_TESTING
  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('classpath:requests/students/add_student.json')
    When method POST
    Then status 201

  @API_TESTING
  Scenario: Get student details
    Given path '/add/student/get'
    And request read('classpath:requests/students/get_student.json')
    When method GET
    Then status 200

