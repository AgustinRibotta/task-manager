-- =========================
-- PERMISSIONS
-- =========================
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
(22, 'projects:assign:manager'),
(23, 'projects:create:tasks'),
(24, 'projects:assign:users')
ON CONFLICT (id) DO NOTHING;


-- =========================
-- ROLES
-- =========================
INSERT INTO role_data (id, name)
VALUES
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'USER'),
(4, 'CLIENT')
ON CONFLICT (id) DO NOTHING;


-- =========================
-- ADMIN (ALL PERMISSIONS)
-- =========================
INSERT INTO role_permission (role_id, permission_id)
SELECT 1, id FROM permission
ON CONFLICT DO NOTHING;


-- =========================
-- MANAGER
-- =========================
INSERT INTO role_permission (role_id, permission_id)
VALUES
(2, 2),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 18),
(2, 20),
(2, 23),
(2, 24),
(2, 1)
ON CONFLICT DO NOTHING;


-- =========================
-- USER
-- =========================
INSERT INTO role_permission (role_id, permission_id)
VALUES
(3, 2),
(3, 13),
(3, 14),
(3, 15),
(3, 18),
(3, 23)
ON CONFLICT DO NOTHING;


-- =========================
-- CLIENT
-- =========================
INSERT INTO role_permission (role_id, permission_id)
VALUES
(4, 12),
(4, 17)
ON CONFLICT DO NOTHING;


-- =========================
-- USERS
-- =========================
INSERT INTO user_data (id, username, password, email)
VALUES
(1, 'admin', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'admin@example.com'),
(2, 'manager', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'manager@example.com'),
(3, 'user', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYHfE4Y4.', 'user@example.com'),
(4, 'client', '$2a$10$iLCE.3nsDFIHMXVN5MhC3.ltbmxIm2Ji.WLqcYid5lpLhYF4Y4.', 'client@example.com')
ON CONFLICT (id) DO NOTHING;


-- =========================
-- USER ROLES
-- =========================
INSERT INTO user_roles (user_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4)
ON CONFLICT DO NOTHING;


-- =========================
-- PROJECTS
-- =========================
INSERT INTO project (id, owner_id, name, description)
VALUES
(1, 1, 'E-Commerce Platform', 'Full e-commerce platform project'),
(2, 2, 'Task Manager SaaS', 'Collaborative task management tool'),
(3, 2, 'HR Management System', 'Human resources management system')
ON CONFLICT (id) DO NOTHING;


-- =========================
-- PROJECT USERS
-- =========================
INSERT INTO project_user (project_id, user_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(3, 1),
(3, 2)
ON CONFLICT DO NOTHING;


-- =========================
-- TASKS
-- =========================
INSERT INTO task (id, name, description, project_id, status)
VALUES
(1, 'Design product model', 'Define database structure', 1, 'DONE'),
(2, 'Implement product catalog', 'Create product listing endpoints', 1, 'IN_PROGRESS'),
(3, 'Shopping cart system', 'Add and manage cart', 2, 'TODO'),
(4, 'Payment integration', 'Connect payment gateway', 1, 'TODO'),
(5, 'Customer user system', 'User registration and login', 1, 'DONE'),
(6, 'Category management', 'CRUD for categories', 2, 'IN_PROGRESS'),
(7, 'Order history', 'View purchase history', 2, 'TODO'),
(8, 'Admin dashboard', 'Admin panel overview', 2, 'TODO'),
(9, 'Discount system', 'Coupons and promotions', 3, 'TODO'),
(10, 'Department management', 'Manage company departments', 3, 'DONE'),
(11, 'My Tasks', 'Personal task management', NULL, 'DONE')
ON CONFLICT (id) DO NOTHING;


-- =========================
-- TASK USERS
-- =========================
INSERT INTO task_user (task_id, user_id)
VALUES
(1, 1),
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
(11, 1)
ON CONFLICT DO NOTHING;

-- =========================
-- RESET SEQUENCES
-- =========================
SELECT setval(pg_get_serial_sequence('permission', 'id'), (SELECT MAX(id) FROM permission));
SELECT setval(pg_get_serial_sequence('role_data', 'id'), (SELECT MAX(id) FROM role_data));
SELECT setval(pg_get_serial_sequence('user_data', 'id'), (SELECT MAX(id) FROM user_data));
SELECT setval(pg_get_serial_sequence('project', 'id'), (SELECT MAX(id) FROM project));
SELECT setval(pg_get_serial_sequence('task', 'id'), (SELECT MAX(id) FROM task));
