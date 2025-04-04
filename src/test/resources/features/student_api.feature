
Feature: Student API Tests

  Background:
    * url 'http://localhost:8080'
    * header Content-Type = 'applicatin/json'
    * def setup =
      """
      function() {
        // Additional instructions for background setup (e.g., config loading)
        // No specific instructions provided in the JSON, so leaving it empty for now.
        return {};
      }
      """
    * def backgroundConfig = setup()

  @API_TESTING
  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('requests/students/add_student.json')
    When method POST
    Then status 201

  @API_TESTING
  Scenario: Get student details
    Given path '/add/student/get'
    And request read('requests/students/get_student.json')
    When method GET
    Then status 200

