# Spring Boot Product API Testing

## Overview
This project is a demonstration of unit testing practices in a Spring Boot REST API. The API provides basic CRUD (Create, Read, Update, Delete) functionality for managing products, and showcases unit testing of various layers including controllers and repositories using JUnit, Mockito, and MockMvc.

The project demonstrates key principles of test-driven development (TDD), mocking dependencies, and validating responses using JSON path assertions, along with testing HTTP status codes.

## Features

- **REST API Endpoints** for managing products.
- **Unit Tests** using **JUnit** and **Mockito**.
- **MockMvc** for testing Spring MVC controllers.
- **JSON Response Validation** using Jackson for proper structure and values.
- **Repository Mocking** to simulate interactions with the database during tests.
- **HTTP Status Verification** to ensure correct status codes are returned for different API requests.

## Technologies Used

- **Spring Boot**: Framework for building the REST API and handling dependency injection, request mapping, and more.
- **JUnit**: The framework used for writing and running unit tests.
- **Mockito**: For mocking the `ProductRepository` during testing to isolate the controller layer from the database.
- **MockMvc**: To simulate HTTP requests and test the Spring MVC controllers without needing to run a full server.
- **Jackson**: Used for JSON processing to serialize and deserialize request and response data.
- **Maven**: Build tool for managing dependencies, building the project, and running tests.

## Setup Instructions

### Prerequisites

- **Java 11+**: Make sure you have Java 11 or later installed on your machine.
- **Maven**: You'll need Maven to build and run the project.
- **IDE**: You can use any IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code for editing the code.


