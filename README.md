# Vehicles
##### - Srikant Vadrevu

### Project Details

 - Developed web services in java using Jersey library (JAX-RS).
 - Used Google Cloud SQL as backend Database.
 - Deployed application on Heroku Platform (URL: http://vadrevu-stackflownover.herokuapp.com/).
 - Used Junit for automated testing of webservice.
 - Used Junit and Selenium WebDriver for testing the UI of the application.
 - Used Jquery on the client side for AJAX requests to the web services.
 - Used JSON as request and response format for the API.

### End Point URL's and their Descriptions

 - http://vadrevu-stackflownover.herokuapp.com/ --> Application Home Page
 - http://vadrevu-stackflownover.herokuapp.com/webapi/getData --> End point for GET (To retrive all vehicle data in database), POST (Insert) and PUT (Update) requests.
 - http://vadrevu-stackflownover.herokuapp.com/webapi/getData/{id} --> To get details of specific vehicle in database (GET).
 - http://vadrevu-stackflownover.herokuapp.com/webapi/getData/query? --> For filtering results with make,model and year as parameter names (GET).
 - http://vadrevu-stackflownover.herokuapp.com/webapi/getData/{id} --> To DELETE details of specific vehicle in database. 
 
 
### Code Links

- https://github.com/srikantv92/Vehicles/tree/master/src/main/java/org/mitchell/international/Vehicles ---> Contains classes for web service.
  GetData -> Webservice Code.
  BaseClass -> Database Connections.
  Vehicle -> Vehicle class having properties of vehicles as attributes.
  
- https://github.com/srikantv92/Vehicles/tree/master/src/main/java/org/mitchell/international/TestCases ---> Contains test cases for web service and web application.
  TestcasesApi -> Testcases for web services.
  WebApplicationTestcases -> Testcases for web application.
  Resources -> Class for storing locators of web elements.
  BaseClassTest -> Pre and post testcase conditions for WebApplicationTestcases.
  
- https://github.com/srikantv92/Vehicles/tree/master/src/main/webapp ---> Client side code.
  index.html -> Homepage of application.
  custom.js -> Javascript for validating fields in client side and AJAX requests to webservices.
 
