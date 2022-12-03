-- liquibase formatted sql
-- changeset esley:1
CREATE TABLE PLAYER
(
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    years_old BIGINT NOT NULL
);



