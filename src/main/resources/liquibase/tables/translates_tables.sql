--liquibase formatted sql

--changeset anisimovSA:TRANSLATES.create_translates
CREATE TABLE IF NOT EXISTS translates.translates
(
    id        BIGSERIAL PRIMARY KEY,
    origin    TEXT NOT NULL,
    translate TEXT NOT NULL
);

CREATE INDEX origin_idx ON translates.translates (origin);
CREATE INDEX translate_idx ON translates.translates (translate);

CREATE TABLE IF NOT EXISTS translates.user_translates
(
    user_id      BIGINT                                       NOT NULL,
    translate_id BIGINT REFERENCES translates.translates (id) NOT NULL,
    counter      BIGINT                                       NOT NULL DEFAULT 1,
    PRIMARY KEY (user_id, translate_id)
);