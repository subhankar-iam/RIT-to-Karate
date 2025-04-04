
Feature: Student API Tests

  Background:
    * url 'http://localhost:8000'
    * header Content-Type = 'applicatin/json'
    * def saveRequest = function(requestName, requestBody){
        java.nio.file.Files.write(java.nio.file.Paths.get('requests/students/', requestName), karate.toJson(requestBody).getBytes(java.nio.charset.StandardCharsets.UTF_8));
      }

  @API_TESTING
  Scenario: Add a new student
    Given path '/api/student/add'
    And request read('classpath:requests/students/add_student.json')
    When method POST
    Then status 201

  @API_TESTING
  Scenario: Get student details
    Given path '/api/student/get'
    And request read('classpath:requests/students/get_student.json')
    When method GET
    Then status 200

