
@API_TESTING
Feature: Student Management API Tests

  Background:
    * def baseUrl = 'http://localhost:8080'
    * header Content-Type = 'applicatin/json'
    * def configure =
    """
    function(){
       // Additional configurations can be placed here (e.g., MongoDB connection, Kafka setup)
       return {};
    }
    """
    * def config = configure()

  @add_student
  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('classpath:features/requests/students/add_student.json')
    When method POST
    Then status 201

  @get_student
  Scenario: Get student details
    Given path '/add/student/get'
    And request read('classpath:features/requests/students/get_student.json')
    When method GET
    Then status 200

