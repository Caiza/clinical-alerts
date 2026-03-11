CREATE TABLE patient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    date_of_birth DATE,
    gender VARCHAR(50),
    status BOOLEAN,
    number_id INTEGER
);