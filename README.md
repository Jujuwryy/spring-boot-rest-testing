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

### Installation Steps
1. Clone the repository:
```bash
git clone https://github.com/[username]/spring-boot-product-api-testing.git
```

2. Navigate to the project directory:
```bash
cd spring-boot-product-api-testing
```

3. Build the project:
```bash
mvn clean install
```

4. Run the tests:
```bash
mvn test
```

## Project Structure
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── product
│   │               ├── controller
│   │               │   └── ProductController.java
│   │               ├── model
│   │               │   └── Product.java
│   │               ├── repository
│   │               │   └── ProductRepository.java
│   │               └── service
│   │                   └── ProductService.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com
            └── example
                └── product
                    └── controller
                        └── ProductControllerTest.java
```

## API Endpoints

### GET /api/products
- Retrieves all products
- Returns HTTP 200 OK with product list

### GET /api/products/{id}
- Retrieves a specific product by ID
- Returns HTTP 200 OK if found
- Returns HTTP 404 Not Found if product doesn't exist

### POST /api/products
- Creates a new product
- Request body should contain product details
- Returns HTTP 201 Created with created product

### PUT /api/products/{id}
- Updates an existing product
- Request body should contain updated product details
- Returns HTTP 200 OK with updated product
- Returns HTTP 404 Not Found if product doesn't exist

### DELETE /api/products/{id}
- Deletes a product by ID
- Returns HTTP 204 No Content on success
- Returns HTTP 404 Not Found if product doesn't exist

## Testing Strategy

### Unit Tests
The project includes comprehensive unit tests that cover:
- Controller layer testing using MockMvc
- Service layer testing with mocked repositories
- Input validation testing
- Error handling scenarios
- Response content validation

### Test Examples
```java
@Test
public void whenGetProducts_thenReturnsJsonArray() throws Exception {
    mockMvc.perform(get("/api/products")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].name", is("Product 1")));
}
```

## Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
