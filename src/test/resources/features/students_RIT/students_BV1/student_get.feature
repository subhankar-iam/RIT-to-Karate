
Feature: Get Student API

  Background:
    * url 'http://localhost:8080'

  Scenario: Get student details
    Given path '/api/student/get'
    And request read('classpath:requests/students_RIT/students_BV1/student_get.json')
    When method GET
    Then status 200

