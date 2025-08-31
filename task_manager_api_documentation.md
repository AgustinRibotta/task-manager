# Task Manager API Documentation

**Base URL:** `http://localhost:8080/api/v1`
**Authentication:** JWT Bearer Token (HS256)

---

## 1. Authentication

### **Login User**

* **POST** `/auth/login`
* **Body (JSON):**

```json
{
  "username": "test4",
  "password": "test"
}
```

* **Response:**

  * 200 OK: `{ "token": "jwt_token_here" }`
  * 401 Unauthorized: Invalid credentials
* **Access:** Public

### **Register User**

* **POST** `/auth/users`
* **Body (JSON):**

```json
{
  "email": "test@gemail.com",
  "username": "test6",
  "password": "test",
  "roleDtos": [{"id": 1}]
}
```

* **Response:**

  * 201 Created: User created
  * 400 Bad Request: Invalid data
  * 409 Conflict: User already exists
* **Access:** ADMIN

---

## 2. Users

| Endpoint           | Method | Roles / Access                           | Description       |
| ------------------ | ------ | ---------------------------------------- | ----------------- |
| `/auth/users`      | GET    | ADMIN                                    | Get all users     |
| `/auth/users/{id}` | GET    | ADMIN or the user itself (`isUser(#id)`) | Get user by ID    |
| `/auth/users/{id}` | PUT    | ADMIN or the user itself (`isUser(#id)`) | Update user by ID |
| `/auth/users/{id}` | DELETE | ADMIN                                    | Delete user by ID |

**User JSON Example (POST/PUT):**

```json
{
  "username": "test6",
  "email": "test@gemail.com",
  "password": "test",
  "roleDtos": [{"id": 1}]
}
```

---

## 3. Roles

| Endpoint      | Method | Roles / Access | Description     |
| ------------- | ------ | -------------- | --------------- |
| `/roles`      | GET    | Public         | Get all roles   |
| `/roles/{id}` | GET    | Public         | Get role by ID  |
| `/roles`      | POST   | ADMIN          | Create new role |
| `/roles/{id}` | PUT    | ADMIN          | Update role     |
| `/roles/{id}` | DELETE | ADMIN          | Delete role     |

**Role JSON Example (POST/PUT):**

```json
{
  "name": "admin"
}
```

---

## 4. Projects

| Endpoint                 | Method | Roles / Access                               | Description          |
| ------------------------ | ------ | -------------------------------------------- | -------------------- |
| `/projects/all`          | GET    | ADMIN                                        | Get all projects     |
| `/projects/project/{id}` | GET    | ADMIN or associated users (`isProject(#id)`) | Get project by ID    |
| `/projects/project/new`  | POST   | ADMIN                                        | Create new project   |
| `/projects/project/{id}` | PUT    | ADMIN or associated users (`isProject(#id)`) | Update project by ID |
| `/projects/project/{id}` | DELETE | ADMIN                                        | Delete project by ID |

**Project JSON Example (POST/PUT):**

```json
{
  "name": "project4",
  "description": "test5",
  "usersDtos":[
    { "id": 1 }
  ]
}
```

---

## 5. Tasks

| Endpoint           | Method | Roles / Access                                     | Description       |
| ------------------ | ------ | -------------------------------------------------- | ----------------- |
| `/tasks/all`       | GET    | ADMIN                                              | Get all tasks     |
| `/tasks/task/{id}` | GET    | ADMIN, MANAGER or associated users (`isTask(#id)`) | Get task by ID    |
| `/tasks/task/new`  | POST   | ADMIN, MANAGER                                     | Create new task   |
| `/tasks/task/{id}` | PUT    | ADMIN, MANAGER or associated users (`isTask(#id)`) | Update task by ID |
| `/tasks/task/{id}` | DELETE | ADMIN, MANAGER                                     | Delete task by ID |

**Task JSON Example (POST/PUT):**

```json
{
  "name": "new22",
  "description": "new task",
  "status": "DONE",
  "userSummaryDto": [
    { "id": 1 }
  ]
}
```

---

## 6. Security Notes

* JWT authentication is required for restricted endpoints.
* Roles must be in the format `ROLE_XXX` in the token.
* Spring Security configuration:

  * Public: `/auth/login`, `/projects/**`, `/tasks/**`
  * ADMIN: `/auth/users/**`, `/roles/**`, `/projects/new`
  * Others: Authenticated
* `@PreAuthorize` methods implement access based on:

  * `isUser(#id)` → user owns the resource
  * `isProject(#id)` → user associated with the project
  * `isTask(#id)` → user associated with the task


