--1. ТАБЛИЦЯ READER (Читачі)
CREATE TABLE reader (
    id                  bigserial PRIMARY KEY,
    first_name          character varying(100) NOT NULL,
    last_name           character varying(100) NOT NULL,
    phone_number        character varying(20),
    email               character varying(100) UNIQUE NOT NULL,
    active_orders_count integer DEFAULT 0
);

-- 2. ТАБЛИЦЯ LIBRARIAN (Бібліотекарі)
CREATE TABLE librarian (
    id                  bigserial PRIMARY KEY,
    first_name          character varying(100) NOT NULL,
    last_name           character varying(100) NOT NULL,
    position            character varying(100)
);

-- 3. ТАБЛИЦЯ BOOK (Книги)
CREATE TABLE book (
    id                  bigserial PRIMARY KEY,
    title               character varying(500) NOT NULL,
    author              character varying(200) NOT NULL,
    isbn                character varying(20) UNIQUE,
    publication_year    integer,

    -- Поля для відстеження примірників
    inventory_number    bigint UNIQUE NOT NULL,
    is_available        boolean DEFAULT TRUE,
    location            character varying(50), -- Subscription/Reading Room

    -- Зв'язок: Хто зараз тримає книгу
    current_reader_id   bigint,
    due_date            date,

    -- Зовнішній ключ
    CONSTRAINT fk_current_reader
        FOREIGN KEY (current_reader_id)
        REFERENCES reader (id)
);