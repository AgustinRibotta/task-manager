# 🏗️ Gestor de Tareas con Usuarios y Roles (Trello básico)

## 💡 ¿Qué es?

Una **API backend** que permite:

- Gestionar usuarios y su autenticación.
- Crear y organizar **proyectos**.
- Dentro de cada proyecto, tener **tareas** con diferentes estados.
- Controlar qué usuarios pueden hacer qué acciones según su **rol**:
  - `ADMIN`
  - `PROJECT_MANAGER`
  - `USER`

---

## 🧱 Arquitectura general

### 📁 Entidades principales

| Entidad     | Descripción |
|-------------|-------------|
| `Usuario`   | Tiene nombre, email, contraseña (hashed), y un rol (`ADMIN`, `PROJECT_MANAGER`, `USER`) |
| `Proyecto`  | Grupo de tareas. Tiene nombre, descripción, y un dueño (PROJECT_MANAGER o ADMIN) |
| `Tarea`     | Pertenece a un proyecto. Tiene título, estado (`TODO`, `IN_PROGRESS`, `DONE`), prioridad, etc. |

---

## 🔐 Seguridad y Autenticación

- Autenticación con **JWT** (generado con `jjwt`).
- Validación de tokens con **Spring Security + OAuth2 Resource Server**.
- Los roles se incluyen en el JWT (`roles: ["PROJECT_MANAGER"]`, por ejemplo).
- Acceso restringido con anotaciones como `@PreAuthorize`.
- Endpoints públicos:
  - `/login`
  - `/register`
- Endpoints protegidos:
  - `/api/usuarios`
  - `/api/proyectos`
  - `/api/tareas`

---

## 🔄 Flujo básico del sistema

1. El usuario se registra (`/register`), elige su rol o se le asigna.
2. Se autentica (`/login`) y recibe un JWT.
3. Usa ese token en sus peticiones autenticadas.
4. Dependiendo del rol:
   - `ADMIN`: acceso completo al sistema, gestión de usuarios.
   - `PROJECT_MANAGER`: puede crear y gestionar proyectos y tareas propias.
   - `USER`: puede ver tareas asignadas, colaborar en proyectos.


## 🧠 Tecnologías usadas

| Tecnología                   | Rol |
|-----------------------------|-----|
| Spring Boot                 | Base del proyecto |
| Spring Security             | Seguridad y control de acceso |
| OAuth2 Resource Server      | Validación de JWT |
| JJWT                        | Generación y parsing de tokens |
| Spring Data JPA             | Persistencia de datos |
| PostgreSQL                  | Base de datos relacional |
| Liquibase                   | Versionado del schema |
| Bean Validation             | Validaciones en DTOs |
| MapStruct                   | Mapeo entre entidades y DTOs |
| Lombok                     | Reducción de boilerplate |

---
