-- ===================================
-- Roles
-- ===================================
INSERT INTO role_data (name) VALUES ('ADMIN')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO role_data (name) VALUES ('USER')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO role_data (name) VALUES ('MANAGER')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO role_data (name) VALUES ('DEVELOPER')
    ON CONFLICT (name) DO NOTHING;

-- ===================================
-- Users
-- ===================================
INSERT INTO user_data (username, password, email) VALUES
('admin', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'admin@example.com'),
('johndoe', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'johndoe@example.com'),
('janedoe', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'janedoe@example.com');

-- ===================================
-- User Roles
-- ===================================
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1), 
(1, 2), 
(2, 2), 
(3, 2);

-- ===================================
-- Projects
-- ===================================
INSERT INTO project (id, name, description) VALUES
(1, 'E-Commerce Platform','Plataforma de comercio electrónico completa que permite a los usuarios navegar productos, gestionar carrito de compras, realizar pagos y seguir el estado de sus pedidos. Incluye panel de administración para gestión de inventario, usuarios y reportes de ventas.'),
(2, 'Task Manager SaaS','Herramienta colaborativa para la gestión de tareas y proyectos en equipos de desarrollo. Permite crear proyectos, asignar tareas, establecer prioridades, fechas límite y visualizar el progreso mediante tableros tipo Kanban.'),
(3, 'HR Management System', 'Sistema de gestión de recursos humanos que permite administrar empleados, roles, asistencia, vacaciones y evaluaciones de desempeño. Incluye reportes y panel administrativo para supervisión de la organización.'),
(4, 'Real-Time Chat App', 'Aplicación de mensajería en tiempo real que permite comunicación entre usuarios individuales y en grupos. Incluye notificaciones, estado de conexión y almacenamiento de historial de mensajes.');
-- ===================================
-- Project Users
-- ===================================
INSERT INTO project_user (project_id, user_id) VALUES
(1, 1), 
(1, 2), 
(2, 3); 

-- ===================================
-- Tasks
-- ===================================
INSERT INTO task (id, name, description, project_id, status) VALUES
(1, 'Diseñar modelo de productos', 'Definir estructura de base de datos para productos, categorías, precios, stock e imágenes.', 1, 'DONE'),
(2, 'Implementar catálogo de productos', 'Crear endpoints para listar, filtrar y buscar productos con paginación.', 1, 'IN_PROGRESS'),
(3, 'Sistema de carrito de compras', 'Desarrollar funcionalidad de agregar, actualizar y eliminar productos del carrito.', 1, 'TODO'),
(4, 'Integración de pagos', 'Conectar la aplicación con pasarela de pagos para procesar transacciones de forma segura.', 1, 'TODO'),
(17, 'Sistema de usuarios clientes', 'Implementar registro, login y gestión de perfiles de clientes con datos personales y direcciones.', 1, 'DONE'),
(18, 'Gestión de categorías', 'Crear sistema para organizar productos por categorías con CRUD completo.', 1, 'IN_PROGRESS'),
(19, 'Historial de pedidos', 'Permitir a los usuarios ver el historial completo de compras realizadas.', 1, 'TODO'),
(20, 'Panel de administración', 'Desarrollar dashboard para administración de productos, usuarios y ventas.', 1, 'TODO'),
(21, 'Sistema de descuentos', 'Implementar cupones y descuentos aplicables a productos o carritos.', 1, 'TODO'),
(27, 'Gestión de departamentos', 'Crear y administrar departamentos dentro de la empresa.', 3, 'DONE'),
(28, 'Asignación de roles internos', 'Permitir asignar roles específicos a empleados dentro de la organización.', 3, 'IN_PROGRESS'),
(29, 'Reporte de asistencia mensual', 'Generar reportes de asistencia por empleado y departamento.', 3, 'TODO'),
(30, 'Sistema de nómina', 'Módulo para cálculo básico de salarios y pagos mensuales.', 3, 'TODO'),
(31, 'Evaluaciones por objetivos', 'Permitir definir objetivos y evaluar cumplimiento por empleado.', 3, 'TODO'),
(32, 'Indicador de usuario en línea', 'Mostrar si un usuario está conectado o desconectado en tiempo real.', 4, 'DONE'),
(33, 'Mensajes vistos (read receipts)', 'Indicar cuándo un mensaje ha sido leído por el receptor.', 4, 'IN_PROGRESS'),
(34, 'Envío de archivos', 'Permitir enviar imágenes y documentos en el chat.', 4, 'TODO'),
(35, 'Búsqueda de mensajes', 'Implementar búsqueda dentro del historial de conversaciones.', 4, 'TODO'),
(36, 'Eliminación de mensajes', 'Permitir eliminar mensajes enviados con control de permisos.', 4, 'TODO');

-- ===================================
-- Task Users
-- ===================================
INSERT INTO task_user (task_id, user_id) VALUES
(1, 1), 
(2, 2),
(3, 3); 
