# Task Manager API 🚀

A RESTful API built with **Spring Boot 3** for managing tasks, projects, users, and roles.
The application implements secure authentication and authorization using **JWT** and **Spring Security**, following a layered and scalable backend architecture.

---

# 📖 Project Overview

The goal of this project is to provide a complete backend system for task and project management in collaborative environments.

The API allows administrators and managers to:

* Manage users and roles
* Create and organize projects
* Assign tasks to users
* Control access permissions based on roles
* Secure endpoints using JWT authentication

The project follows modern backend development practices using:

* REST architecture
* DTO pattern
* Layered architecture
* Database versioning
* Secure authentication mechanisms
* API documentation standards

---

# 🛠️ Technologies & Tools

| Technology            | Purpose                            |
| --------------------- | ---------------------------------- |
| **Java 17**           | Main programming language          |
| **Spring Boot 3.5.3** | Backend framework                  |
| **Spring Web**        | REST API development               |
| **Spring Security**   | Authentication & authorization     |
| **JWT (jjwt)**        | Token-based authentication         |
| **Spring Data JPA**   | Database access layer              |
| **PostgreSQL**        | Relational database                |
| **Liquibase**         | Database migrations/versioning     |
| **MapStruct**         | Entity ↔ DTO mapping               |
| **Lombok**            | Boilerplate code reduction         |
| **Bean Validation**   | Request validation                 |
| **Swagger / OpenAPI** | API documentation                  |
| **Maven**             | Dependency management & build tool |

---

# 🔐 Security

Authentication is implemented using **JWT Bearer Tokens**.

After login, the API generates a signed token that must be included in protected requests.

Example:

```http
Authorization: Bearer <jwt-token>
```

The system supports **role-based authorization** with different access levels:

* **ADMIN**
* **MANAGER**
* **USER**

Spring Security is used to secure endpoints and validate permissions.

---

# 🗄️ Database Management

The project uses **PostgreSQL** as the main database.

Database schema changes are managed through **Liquibase**, allowing:

* Version-controlled migrations
* Safer database updates
* Consistent environments across development and production

---

# 📚 API Documentation

Swagger/OpenAPI integration provides interactive API documentation and endpoint testing.

Once the application is running:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 🧩 Main Features

## Authentication

* Login with JWT
* Secure token validation

## User Management

* Create users
* Update users
* Assign roles

## Role Management

* Create and manage system roles
* Authorization control

## Project Management

* Create projects
* Assign users to projects
* Manage project status

## Task Management

* Create tasks
* Assign tasks to users
* Track task status

---

# 🏗️ Architecture

The project follows a layered architecture:

```text
Controller → Service → Repository → Database
```

### Main Layers

| Layer      | Responsibility          |
| ---------- | ----------------------- |
| Controller | Handle HTTP requests    |
| Service    | Business logic          |
| Repository | Database operations     |
| DTO        | Data transfer objects   |
| Mapper     | Entity ↔ DTO conversion |

This structure improves:

* Maintainability
* Scalability
* Separation of concerns
* Testability

---

# ⚙️ Development Features

The project also includes:

* Input validation with Bean Validation
* Global exception handling
* DTO mapping with MapStruct
* Hot reload with Spring DevTools
* Testing support with Spring Boot Test

---

# 📄 License

This project is intended for educational and backend development purposes.
