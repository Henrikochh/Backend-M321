# Benutzerverwaltung

This is a user management service for a car rental company.

## How to run the application

1.  Start the PostgreSQL database:
    ```bash
    docker-compose up -d
    ```

2.  Start the Spring Boot application:
    ```bash
    ./gradlew bootRun
    ```

## How to test the application

You can use the following `curl` commands to test the application:

*   **Get all users:**
    ```bash
    curl http://localhost:8085/users
    ```

*   **Create a new user:**
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}' http://localhost:8085/users
    ```

*   **Get a user by ID:**
    ```bash
    curl http://localhost:8085/users/1
    ```
