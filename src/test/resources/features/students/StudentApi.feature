
Feature: Student Management API

  Background:
    * url 'http://localhost:8080'
    * header Content-Type = 'application/json'
    * def setup = 
    """
    function(){
      // Additional setup tasks if needed - e.g., config loading
      return {};
    }
    """
    * def backgroundResult = setup()

  @API_TESTING
  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('classpath:features/requests/students/add_student.json')
    When method POST
    Then status 201
    * def additionalTask1 = 
    """
    function(){
        //any additional task to perform here.
        return {};
    }
    """
    * def scenario1Result = additionalTask1()

  @API_TESTING
  Scenario: Get student details
    Given path '/add/student/get'
    And request read('classpath:features/requests/students/get_student.json')
    When method GET
    Then status 200
    * def additionalTask2 = 
    """
    function(){
        //any additional task to perform here.
        return {};
    }
    """
    * def scenario2Result = additionalTask2()

