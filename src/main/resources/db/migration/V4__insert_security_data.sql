-- V4__insert_security_data.sql
-- Вставка тестових користувачів та ролей

-- NOTE: Для реальних проектів паролі повинні бути закодовані! Тут використовується NoOpPasswordEncoder.

INSERT INTO users (username, password) VALUES ('1111', '1111');
INSERT INTO users (username, password) VALUES ('2222', '2222');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_User');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_Admin');


INSERT INTO roles_has_users (user_id, role_id) VALUES (1, 1);

INSERT INTO roles_has_users (user_id, role_id) VALUES (2, 1);
INSERT INTO roles_has_users (user_id, role_id) VALUES (2, 2);