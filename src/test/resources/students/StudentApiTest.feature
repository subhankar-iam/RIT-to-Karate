
Feature: Student API Tests

  @API_TESTING
  Background:
    * url 'http://localhost:8080'
    * header Content-Type = 'applicatin/json'
    * def readConfigFile = function(path) { return read(path) }
    * def saveRequest = function(requestName, requestBody) {
        java.nio.file.Files.write(java.nio.file.Paths.get('requests/students', requestName), requestBody.getBytes(java.nio.charset.StandardCharsets.UTF_8));
      }

  @API_TESTING
  Scenario: Add a new student
    * def requestBody = read('requests/students/add_student.json')
    Given path '/add/student/add'
    And request requestBody
    When method POST
    Then status 201

  @API_TESTING
  Scenario: Get student details
    * def requestBody = read('requests/students/get_student.json')
    Given path '/add/student/get'
    And request requestBody
    When method GET
    Then status 200
