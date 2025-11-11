-- V3__create_security_tables.sql
-- Створення таблиць для Spring Security та R2DBC

create table users
(
    id bigserial primary key not null,
    username varchar(100) UNIQUE NOT NULL,
    password varchar(100)
);

create table roles
(
    id bigserial primary key not null,
    name varchar(100) UNIQUE NOT NULL
);

create table roles_has_users
(
    id bigserial primary key not null,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

ALTER TABLE roles_has_users
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE roles_has_users
    ADD CONSTRAINT fk_role_id
        FOREIGN KEY (role_id) REFERENCES roles(id);