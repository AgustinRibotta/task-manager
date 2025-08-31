# 🏗️ Task Manager with Users and Roles (Basic Trello)

## 💡 What is it?

A **backend API** that allows you to:

* Manage users and their authentication.
* Create and organize **projects**.
* Within each project, manage **tasks** with different states.
* Control what users can do based on their **role**:

  * `ADMIN`
  * `PROJECT_MANAGER`
  * `USER`

---

## 🧱 General Architecture

### 📁 Main Entities

| Entity    | Description                                                                              |
| --------- | ---------------------------------------------------------------------------------------- |
| `User`    | Has a name, email, password (hashed), and a role (`ADMIN`, `PROJECT_MANAGER`, `USER`)    |
| `Project` | Group of tasks. Has a name, description, and an owner (PROJECT\_MANAGER or ADMIN)        |
| `Task`    | Belongs to a project. Has a title, state (`TODO`, `IN_PROGRESS`, `DONE`), priority, etc. |

---

## 🔐 Security and Authentication

* Authentication with **JWT** (generated with `jjwt`).
* Token validation with **Spring Security + OAuth2 Resource Server**.
* Roles are included in the JWT (`roles: ["PROJECT_MANAGER"]`, for example).
* Access restrictions with annotations like `@PreAuthorize`.
* Public endpoints:

  * `/login`
  * `/register`
* Protected endpoints:

  * `/api/users`
  * `/api/projects`
  * `/api/tasks`

---

## 🔄 Basic System Flow

1. A user registers (`/register`), choosing a role or being assigned one.
2. The user authenticates (`/login`) and receives a JWT.
3. They use that token for authenticated requests.
4. Depending on the role:

   * `ADMIN`: full system access, user management.
   * `PROJECT_MANAGER`: can create and manage their own projects and tasks.
   * `USER`: can view assigned tasks, collaborate on projects.

---

## 🧠 Technologies Used

| Technology             | Purpose                      |
| ---------------------- | ---------------------------- |
| Spring Boot            | Project foundation           |
| Spring Security        | Security and access control  |
| OAuth2 Resource Server | JWT validation               |
| JJWT                   | Token generation and parsing |
| Spring Data JPA        | Data persistence             |
| PostgreSQL             | Relational database          |
| Liquibase              | Schema versioning            |
| Bean Validation        | DTO validations              |
| MapStruct              | Entity-to-DTO mapping        |
| Lombok                 | Reduces boilerplate          |

