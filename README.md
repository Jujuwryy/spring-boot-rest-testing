Spring Boot Book API Testing
A Spring Boot REST API demonstration project showcasing unit testing practices with JUnit and Mockito.
Features

REST API endpoints for managing books
Unit tests using JUnit and Mockito
MockMvc for testing Spring MVC controllers
JSON response validation
Repository mocking examples

Technologies Used

Spring Boot
JUnit
Mockito
MockMvc
Jackson for JSON processing
Maven

Testing Highlights

Controller layer testing
Repository mocking
Response content validation
HTTP status verification
JSON path assertions

Prerequisites

Java 11 or higher
Maven
Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

Getting Started
Clone the repository:git clone https://github.com/Jujuwryy/spring-boot-book-api-testing.git

Build the project:
mvn clean install

Run the tests:
mvn test

Project Structure
src
├── main
│   ├── java
│   │   └── com
│   │       └── george
│   │           └── restapitest
│   │               ├── Book.java
│   │               ├── BookController.java
│   │               └── BookRepo.java
│   └── resources
└── test
    └── java
        └── com
            └── george
                └── restapitest
                    └── BookControllerTest.java

Test Examples
The project includes examples of:

GET endpoint testing
Response body validation
Collection size verification
JSON property testing

Contributing
Feel free to fork the project and submit pull requests.
