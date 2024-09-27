# Student Management API

## Overview

This project is a **Student Management API** built using the [Ktor](https://start.ktor.io/) framework. The application
provides CRUD operations for managing student data. It follows a clean architecture with a **Service** and **Repository
** pattern, ensuring scalability and maintainability.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete student records.
- **Service & Repository Pattern**: Implements a separation of concerns, promoting clean code architecture.
- **Database**:
    - **PostgreSQL** for the production database.
    - **H2** for testing purposes.
- **ORM**: Utilizes the **Exposed** SQL framework for database interaction.
- **Configuration Management**: Managed using **Hoplite** and **YAML** files.
- **Dependency Injection**: Implemented using **Koin**.
- **Connection Pooling**: Handled by **HikariCP**.
- **Exception Handling**: Managed through **Ktor Status Pages**.
- **Serialization**: Uses **kotlinx.serialization** for data serialization.
- **Database Migrations**: Managed using **Flyway**.
- **Test**: Written Unit Tests using **Mockk**, **Kotlin-test**, **Kotlinx-Coroutines**

## Technologies Used

- **Kotlin**: Programming language.
- **Ktor**: Server framework.
- **Koin**: Dependency injection.
- **HikariCP**: Connection pool.
- **PostgreSQL**: Production database.
- **H2**: Testing database.
- **Exposed**: SQL framework.
- **Flyway**: Database migration.
- **Hoplite**: Configuration management.
- **Mockk**: Mockk to mock in the tests.

## Getting Started

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/student-management-api.git
   cd student-management-api

2. Configure the database settings in `application.yaml`:
   ```yaml
   database:
     url: jdbc:postgresql://localhost:5432/your-database
     driver: org.postgresql.Driver
     user: your-username
     password: your-password

3. Run the database migrations: run flywayMigrate
4. Start the application:

## API Endpoints

- GET /students: Retrieve all students.
- GET /students/id/{id}: Retrieve a specific student by ID.
- POST /students: Create a new student.
- PUT /students/{id}: Update an existing student.
- DELETE /students/id/{id}: Delete a student by ID

## Configuration

The application configuration is managed using **Hoplite** and stored in application-dev.yaml.

## Dependencies

All project dependencies are managed via Gradle. Refer to build.gradle.kts for the complete list of dependencies.