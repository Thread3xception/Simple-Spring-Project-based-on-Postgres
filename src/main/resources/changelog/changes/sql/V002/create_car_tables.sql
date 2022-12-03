-- liquibase formatted sql
-- changeset esley:2
CREATE TABLE CAR
(
    id SERIAL PRIMARY KEY,
    plate VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    power DOUBLE PRECISION NOT NULL,
    player_nickname VARCHAR(255)
);



