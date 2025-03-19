
Feature: Add a new student

  Background:
    * url 'http://localhost:8080'
    * def studentAddRequest = read('classpath:requests/students_RIT/students_BV1/student_add.json')

  Scenario: Add a student successfully
    Given path '/api/student/add'
    And request studentAddRequest
    When method POST
    Then status 201

