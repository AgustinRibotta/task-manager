-- ===================================
-- ROLES
-- ===================================
INSERT INTO role_data (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'MANAGER'),
       (4, 'DEVELOPER');

-- ===================================
-- USERS
-- ===================================
INSERT INTO user_data (id, username, password, email)
VALUES (1, 'Agustin', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'admin@example.com'),
       (2, 'johndoe', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'johndoe@example.com'),
       (3, 'janedoe', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'janedoe@example.com');

-- ===================================
-- USER ROLES
-- ===================================
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 2);

-- ===================================
-- PROJECTS
-- ===================================
INSERT INTO project (id, name, description)
VALUES (1, 'E-Commerce Platform', 'Plataforma de comercio electrónico completa...'),
       (2, 'Task Manager SaaS', 'Herramienta colaborativa para la gestión de tareas...'),
       (3, 'HR Management System', 'Sistema de gestión de recursos humanos...'),
       (4, 'Real-Time Chat App', 'Aplicación de mensajería en tiempo real...');

-- ===================================
-- PROJECT USERS (relación usuarios-proyectos)
-- ===================================
INSERT INTO project_user (project_id, user_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 2),
       (4, 3);

-- ===================================
-- TASKS
-- ===================================
INSERT INTO task (id, name, description, project_id, status)
VALUES (1, 'Diseñar modelo de productos', 'Definir estructura de base de datos...', 1, 'DONE'),
       (2, 'Implementar catálogo de productos', 'Crear endpoints para listar productos...', 1, 'IN_PROGRESS'),
       (3, 'Sistema de carrito de compras', 'Agregar y gestionar carrito...', 2, 'TODO'),
       (4, 'Integración de pagos', 'Conectar pasarela de pagos...', 1, 'TODO'),
       (5, 'Sistema de usuarios clientes', 'Registro y login de clientes...', 1, 'DONE'),
       (6, 'Gestión de categorías', 'CRUD de categorías...', 2, 'IN_PROGRESS'),
       (7, 'Historial de pedidos', 'Ver historial de compras...', 2, 'TODO'),
       (8, 'Panel de administración', 'Dashboard admin...', 2, 'TODO'),
       (9, 'Sistema de descuentos', 'Cupones y promociones...', 3, 'TODO'),
       (10, 'Gestión de departamentos', 'Administrar departamentos...', 3, 'DONE');


INSERT INTO task (id, name, description, status)
VALUES (11, 'Mis Tareas', 'Administrar departamentos...', 'DONE');

-- ===================================
-- TASK USERS (asignaciones)
-- ===================================
INSERT INTO task_user (task_id, user_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 2),
       (3, 1),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 2),
       (8, 3),
       (9, 2),
       (10, 1),
        (11, 1);
