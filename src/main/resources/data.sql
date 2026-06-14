-- PERMISSIONS
INSERT INTO permission (id, name)
VALUES
(1, 'users:read:all'),
(2, 'users:read'),
(3, 'users:create'),
(4, 'users:update'),
(5, 'users:delete'),

(6, 'roles:read:all'),
(7, 'roles:read'),
(8, 'roles:create'),
(9, 'roles:update'),
(10, 'roles:delete'),

(11, 'tasks:read:all'),
(12, 'tasks:read'),
(13, 'tasks:create'),
(14, 'tasks:update'),
(15, 'tasks:delete'),
(16, 'tasks:users'),

(17, 'projects:read:all'),
(18, 'projects:read'),
(19, 'projects:create'),
(20, 'projects:update'),
(21, 'projects:delete'),
(22, 'projects:manager'),
(23, 'projects:tasks'),
(24, 'projects:users');



-- ROLES
INSERT INTO role_data (id, name)
VALUES
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'USER'),
(4, 'CLIENT');

-- ADMIN
INSERT INTO role_permission (role_id, permission_id)
SELECT 1, id FROM permission;

-- MANAGER
INSERT INTO role_permission (role_id, permission_id)
VALUES (2, 2),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 18),
(2, 20),
(2, 23),
(2, 24),
(2, 1);

-- USER
INSERT INTO role_permission (role_id, permission_id)
VALUES(3, 2),
(3, 13),
(3, 14),
(3, 15),
(3, 18),
(3, 23);

-- CLIENT
INSERT INTO role_permission (role_id, permission_id)
VALUES
(4, 12),  -- tasks:read
(4, 17);  -- projects:read

-- USERS
INSERT INTO user_data (id, username, password, email)
VALUES (1, 'admin', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'admin@example.com'),
       (2, 'manager', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'johndoe@example.com'),
       (3, 'user', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'janedoe@example.com'),
       (4, 'client', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'janedoe@example.com');

-- USER ROLES
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

-- PROJECTS
INSERT INTO project (id, owner_id, name, description)
VALUES (1, 1, 'E-Commerce Platform', 'Plataforma de comercio electrónico completa...'),
       (2, 2, 'Task Manager SaaS', 'Herramienta colaborativa para la gestión de tareas...'),
       (3, 2, 'HR Management System', 'Sistema de gestión de recursos humanos...');

-- PROJECT USERS
INSERT INTO project_user (project_id, user_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2);

-- TASKS
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

-- TASK USERS
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
