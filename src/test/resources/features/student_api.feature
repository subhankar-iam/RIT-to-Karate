
Feature: Student API Tests

  Background:
    * url 'http://localhost:8000'
    * header content-type = 'applicatin/json'
    # Additional config loading and setup can be done using karate.call() here if needed
    # def loadConfig = function() { karate.call('classpath:config/setup.feature') }
    # * loadConfig()

  @API_TESTING
  Scenario: Add a new student
    Given path '/add/student/add'
    And request read('classpath:requests/students/add_student.json')
    When method POST
    Then status 201
    # Additional instructions implementation (if any)
    # * def additionalTask = function() { karate.log('Performing additional task for add student') }
    # * additionalTask()

  @API_TESTING
  Scenario: Get student details
    Given path '/add/student/get'
    And request read('classpath:requests/students/get_student.json')
    When method GET
    Then status 200
    # Additional instructions implementation (if any)
    # * def additionalTask = function() { karate.log('Performing additional task for get student') }
    # * additionalTask()
