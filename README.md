

# 🗂️ Task Manager — Full Stack Project (Spring Boot + React)

## 🧩 Overview

**Task Manager** is a full-stack web application inspired by **Trello**, designed to help teams **organize work, manage projects, and track tasks** efficiently.

It combines a **Spring Boot** backend for secure data management and API logic with a **React + Vite** frontend for a fast and dynamic user experience.
The system supports **user authentication**, **role-based access control**, and **real-time project collaboration**.

The main goal of this project is to demonstrate how to build a **complete, modern enterprise web application** using today’s most popular tools in both **Java** and **JavaScript ecosystems**, following clean architecture principles and containerized deployment.

---

## 💡 Concept

The platform allows multiple users to collaborate on different projects, each containing multiple tasks that can be tracked through various stages (like “To Do”, “In Progress”, and “Done”).

Every user has a **role**, which defines what actions they can perform within the system:

* 🧑‍💼 **Admin** — manages users, roles, and all projects
* 🧭 **Project Manager** — creates and organizes projects and tasks
* 👤 **User** — can view, update, and comment on tasks assigned to them

This structure creates a **flexible and secure environment** for team coordination, productivity tracking, and project visibility.

---

## 🧱 System Architecture

The project follows a **three-tier architecture**:

```
Frontend (React + Vite)
   ↓ REST API (JWT Auth)
Backend (Spring Boot)
   ↓
Database (PostgreSQL)
```

* **Frontend:** A fast and modern SPA (Single Page Application) built with React, TypeScript, and TailwindCSS.
* **Backend:** A secure REST API built with Spring Boot, handling authentication, business logic, and persistence.
* **Database:** PostgreSQL stores users, roles, projects, and tasks.
* **Deployment:** All components run inside Docker containers orchestrated with Docker Compose.

---

## 🔐 Authentication Flow

1. A user registers or logs in to the application.
2. The backend authenticates the credentials and issues a **JWT (JSON Web Token)**.
3. The frontend stores the token and uses it to make authenticated API requests.
4. Access control is enforced by **Spring Security**, allowing or denying actions based on the user’s role.

---

## ⚙️ Backend — Spring Boot API

The backend manages the application’s business logic, data persistence, and user security.

### Key Features

* **JWT-based authentication**
* **Role-based access control**
* **CRUD operations** for Users, Projects, and Tasks
* **Entity-DTO mapping** via MapStruct
* **Data validation** using Bean Validation
* **Schema versioning** via Liquibase

### Main Technologies

| Technology             | Purpose                  |
| ---------------------- | ------------------------ |
| Spring Boot            | Backend framework        |
| Spring Security        | Role-based authorization |
| OAuth2 Resource Server | JWT validation           |
| PostgreSQL             | Database                 |
| Liquibase              | Schema management        |
| MapStruct              | Entity mapping           |
| JJWT                   | Token generation         |
| Lombok                 | Code simplification      |

---

## 🎨 Frontend — React + Vite App

The frontend provides a modern and responsive UI for interacting with the backend APIs.
It’s designed for speed, simplicity, and scalability — using **React 18**, **Vite**, and **TailwindCSS**.

### Main Features

* Interactive and reactive **dashboard**
* Role-based content display
* **Reusable components** (modals, forms, cards, etc.)
* **Context-based state management** (AuthContext, ProjectContext)
* **Clean and typed codebase** with TypeScript

### Main Technologies

| Technology   | Purpose                        |
| ------------ | ------------------------------ |
| React + Vite | SPA framework & build tool     |
| TypeScript   | Type safety and autocompletion |
| TailwindCSS  | Styling                        |
| Lucide React | Icons                          |
| ESLint       | Linting                        |
| PostCSS      | CSS processing                 |

---

## 🧱 Database Model

Main entities stored in PostgreSQL:

| Table        | Description                                                   |
| ------------ | ------------------------------------------------------------- |
| `user_data`  | Contains user information (username, email, password)         |
| `role`       | Defines user permissions (`ADMIN`, `PROJECT_MANAGER`, `USER`) |
| `user_roles` | Many-to-many link between users and roles                     |
| `project`    | Represents projects created by managers                       |
| `task`       | Represents tasks within projects                              |

---

## 🔄 Data Flow

1. The user logs in through the frontend.
2. The backend authenticates and sends back a JWT token.
3. The frontend uses this token to fetch user data and tasks.
4. Authorized users can perform CRUD operations on projects and tasks.
5. Changes are persisted in PostgreSQL.

---

## 🐳 Deployment with Docker Compose

### Compose Structure

### Commands

```bash
# Stop and clean up
docker compose down --rmi all --volumes --remove-orphans

# Rebuild and run all services
docker compose up --build -d
```

### Access Points

| Service  | URL                                            | Description     |
| -------- | ---------------------------------------------- | --------------- |
| Frontend | [http://localhost:3000](http://localhost:3000) | React app       |
| Backend  | [http://localhost:8080](http://localhost:8080) | Spring Boot API |
| Database | `localhost:5432`                               | PostgreSQL      |

---

## 🧩 Project Goals

* Demonstrate integration between **Java (Spring Boot)** and **JavaScript (React + Vite)**.
* Practice **secure authentication** with **JWT**.
* Apply **role-based access control** in a real-world context.
* Explore **microservice-style containerization** using Docker.
* Implement **clean and scalable architecture** for both frontend and backend.

---

## 🧠 Future Improvements

* ✅ Add drag-and-drop functionality for tasks
* ✅ Implement WebSocket updates for real-time collaboration
* ✅ Add unit and integration testing
* ✅ Introduce email notifications and audit logging
* ✅ Deploy to a cloud environment (AWS / GCP / Render)


