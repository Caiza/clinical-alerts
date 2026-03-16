CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(50) NOT NULL,
    status BOOLEAN NOT NULL,
    number_id INTEGER NOT NULL UNIQUE
);