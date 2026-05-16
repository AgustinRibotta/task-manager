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

  * 200 OK: `{ "token": "" }`
  * 401 Unauthorized: Invalid credentials
* **Access:** Public

### **Register User**

* **POST** `/auth/users`
* **Body (JSON):**

```json
{
  "email": "",
  "username": "",
  "password": "",
  "roleDtos": [{"id": }]
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
| `/auth/users/{id}` | PUT    | ADMIN                                    | Update user by ID |
| `/auth/users/{id}` | DELETE | ADMIN                                    | Delete user by ID |

**User JSON Example Request:**

```json

{
  "username": "",
  "email": "",
  "password": "",
  "roleDtos": [{"id": }]
}

```
**User JSON Example Response:**

```json

{
    "id": ,
    "username": "",
    "email": "",
    "roleDtos": [
        {
            "id": ,
            "name": ""
        },
            {
                "id": ,
                "name": ""
            }
        ],
        "projecDtos": [
            {
                "id": ,
                "name": ""
            }
        ],
        "taskDto": [
            {
                "id": ,
                "name": ""
            }
        ]
},

```
---

## 3. Roles

| Endpoint      | Method | Roles / Access | Description     |
| ------------- | ------ | -------------- | --------------- |
| `/roles`      | GET    | ADMIN         | Get all roles   |
| `/roles/{id}` | GET    | ADMIN         | Get role by ID  |
| `/roles`      | POST   | ADMIN          | Create new role |
| `/roles/{id}` | PUT    | ADMIN          | Update role     |
| `/roles/{id}` | DELETE | ADMIN          | Delete role     |

**Role JSON Example Request:**

```json
{
  "name": ""
}
```
**Role JSON Example Response:**

```json
{
    "id":,
    "name": ""
}
```
---

## 4. Projects

| Endpoint                 | Method | Roles / Access                               | Description          |
| ------------------------ | ------ | -------------------------------------------- | -------------------- |
| `/projects/all`          | GET    | ADMIN                                        | Get all projects     |
| `/projects/project/{id}` | GET    | ADMIN or associated users (`isProject(#id)`) | Get project by ID    |
| `/projects/project/new`  | POST   | ADMIN                                        | Create new project   |
| `/projects/project/{id}` | PUT    | ADMIN, MANAGER                               | Update project by ID |
| `/projects/project/{id}` | DELETE | ADMIN                                        | Delete project by ID |

**Project JSON Example Request:**

```json
{
  "name": "",
  "description": "",
  "usersDtos":[
    { "id": }
  ]
}
```
**Project JSON Example Response:**

```json
{
    "id": ,
    "name": "",
    "description": "",
    "tasksDtos": [
        {
            "id": ,
            "name":""
        },
        {
            "id": ,
            "name": ""
        }
    ],
    "usersDtos": [
        {
            "id": ,
            "username": ""
        },
        {
            "id": ,
            "username": ""
        }
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

**Task JSON Example Request:**

```json
{
  "name": "",
  "description": "",
  "status": "",
  "userSummaryDto": [
    { "id":  }
  ]
}
```
**Task JSON Example Response:**

```json
{
    "id": ,
    "name": "",
    "description": "",
    "projectDto": {
        "id": ,
        "name": ""
    },
    "status": "",
    "userSummaryDto": [
        {
            "id": ,
            "username": ""
        }
    ]
}
```


