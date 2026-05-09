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
(1, 'Project Alpha', 'Primer proyecto de ejemplo'),
(2, 'Project Beta', 'Segundo proyecto de ejemplo');

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
(1, 'Tarea 1', 'Descripción de la tarea 1', 1, 'TODO'),
(2, 'Tarea 2', 'Descripción de la tarea 2', 1, 'IN_PROGRESS'),
(3, 'Tarea 3', 'Descripción de la tarea 3', 2, 'DONE');

-- ===================================
-- Task Users
-- ===================================
INSERT INTO task_user (task_id, user_id) VALUES
(1, 1), 
(2, 2),
(3, 3); 
